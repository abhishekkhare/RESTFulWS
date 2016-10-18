package com.edu.abhi.rest.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.edu.abhi.rest.dao.JDBCHandler;
import com.edu.abhi.rest.domain.Customer;

/**
 * 
 * @author abhishekkhare
 * 
 * 
 *
 */
@Path("/CustomerResourcePathParam")
public class CustomerResourcePathParam {

	public CustomerResourcePathParam() {
	}

	/**
	 * Sample XML:: <customer><first-name>Abhishek</first-name>
	 * <last-name>Khare</last-name><street>ABC Drive</street><city>Sunny</city>
	 * <state>CA</state><zip>565</zip><country>USA</country></customer>
	 * 
	 * URl - http://localhost:8080/RESTFullWS/rest/CustomerResourcePathParam
	 * 
	 * Type - application/XML
	 * 
	 * @param is
	 * @return
	 */
	@POST
	@Consumes("application/xml")
	public Response createCustomer(InputStream is) {
		Customer customer = readCustomer(is);
		customer.setId(JDBCHandler.getIdCounter().incrementAndGet());
		System.out.println(customer);
		JDBCHandler.getCustomerDB().put(customer.getId(), customer);
		System.out.println("Created customer " + customer.getId());
		return Response.created(URI.create("/customers/" + customer.getId())).build();

	}

	/**
	 * URL - http://localhost:8080/RESTFullWS/rest/CustomerResourcePathParam/1
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public StreamingOutput getCustomer(@PathParam("id") int id) {
		Set<Integer> keys = JDBCHandler.getCustomerDB().keySet();
		for (Integer integer : keys) {
			System.out.println(JDBCHandler.getCustomerDB().get(integer));
		}
		final Customer customer = JDBCHandler.getCustomerDB().get(id);
		if (customer == null) {
			System.out.println("current is null");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return new StreamingOutput() {
			public void write(OutputStream outputStream) throws IOException, WebApplicationException {
				outputCustomer(outputStream, customer);
			}
		};
	}

	/**
	 * URL - http://localhost:8080/RESTFullWS/rest/CustomerResourcePathParam/1/regexp
	 * 
	 * Note:
	 * 
	 * 1 - Since the two methods 'getCustomer' and 'getCustomerRegExp' have the
	 * same set of params, we cannot overload it and hence the name of the two
	 * methods needs to be different
	 * 
	 * 2 - also note that although we are trying to find a customer using
	 * his/her id, we do need to ensure that the url pattern are different for
	 * 'getCustomer' and 'getCustomerRegExp'. this is because the container has
	 * no information on how to route the request.
	 * 
	 * 3 - defines a way to specify the possible path values using regular
	 * expressions
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("{id : \\d+}/regexp")
	@Produces("application/xml")
	public StreamingOutput getCustomerRegExp(@PathParam("id") int id) {
		final Customer customer = JDBCHandler.getCustomerDB().get(id);
		if (customer == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return new StreamingOutput() {
			public void write(OutputStream outputStream) throws IOException, WebApplicationException {
				outputCustomer(outputStream, customer);
			}
		};
	}

	/**
	 * URL - http://localhost:8080/RESTFullWS/rest/CustomerResourcePathParam/Abhishek-Khare
	 * 
	 * Note: this is another GET request, but since the path params and the
	 * number of params are different, rest will be able to identify it.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	@GET
	@Path("{first}-{last}")
	@Produces("application/xml")
	public StreamingOutput getCustomer(@PathParam("first") String firstName, @PathParam("last") String lastName) {
		final Customer customer = JDBCHandler.getUserByName(firstName, lastName);
		if (customer == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return new StreamingOutput() {
			public void write(OutputStream outputStream) throws IOException, WebApplicationException {
				outputCustomer(outputStream, customer);
			}
		};
	}

	/**
	 * URL -
	 * http://localhost:8080/RESTFullWS/rest/CustomerResourcePathParam/Abhishek-Khare/regexp
	 * 
	 * Note:
	 * 
	 * 1 - Since the two methods 'getCustomer' and 'getCustomerFirstLast' have
	 * the same set of params, we cannot overload it and hence the name of the
	 * two methods needs to be different
	 * 
	 * 2 - also note that although we are trying to find a customer using
	 * his/her name, we do need to ensure that the url pattern are different for
	 * 'getCustomer' and 'getCustomerFirstLast'. this is because the container
	 * has no information on how to route the request.
	 * 
	 * 3 - defines a way to specify the possible path values using regular
	 * expressions, so a url like this
	 * http://localhost:8080/RESTFullWS/rest/CustomerResourcePathParam/Abhi1-Khare2/regexp will
	 * not work, you will get HTTP Status 404 - Not Found. However this will
	 * work because of the other method -
	 * http://localhost:8080/RESTFullWS/rest/CustomerResourcePathParam/Abhi1-Khare2
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	@GET
	@Path("{first : [a-zA-Z]+}-{last:[a-zA-Z]+}/regexp")
	@Produces("application/xml")
	public StreamingOutput getCustomerFirstLast(@PathParam("first") String firstName,
			@PathParam("last") String lastName) {
		final Customer customer = JDBCHandler.getUserByName(firstName, lastName);
		if (customer == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return new StreamingOutput() {
			public void write(OutputStream outputStream) throws IOException, WebApplicationException {
				outputCustomer(outputStream, customer);
			}
		};
	}

	/**
	 * Sample XML: <customer><first-name>Abhishek</first-name>
	 * <last-name>Khare</last-name><street>ABC Drive</street><city>Sunny</city>
	 * <state>CA</state><zip>56125</zip><country>USA</country></customer>
	 * 
	 * URL - http://localhost:8080/RESTFullWS/rest/CustomerResourcePathParam/1
	 * 
	 * Type - application/xml
	 * 
	 * Note: the url is same as GET, however the request type is PUT and has an
	 * input parameter of type application/xml.
	 * 
	 * @param id
	 * @param is
	 */
	@PUT
	@Path("{id}")
	@Consumes("application/xml")
	public void updateCustomer(@PathParam("id") int id, InputStream is) {
		Customer update = readCustomer(is);
		Customer current = JDBCHandler.getCustomerDB().get(id);
		if (current == null) {
			System.out.println("current is null");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		current.setFirstName(update.getFirstName());
		current.setLastName(update.getLastName());
		current.setStreet(update.getStreet());
		current.setState(update.getState());
		current.setZip(update.getZip());
		current.setCountry(update.getCountry());
	}

	/**
	 * Sample XML: <customer><first-name>Abhishek</first-name>
	 * <last-name>Khare</last-name><street>ABC Drive</street><city>Sunny</city>
	 * <state>CA</state><zip>78957</zip><country>USA</country></customer>
	 * 
	 * URL - http://localhost:8080/RESTFullWS/rest/CustomerResourcePathParam/Abhishek-Khare
	 * 
	 * Type - application/xml
	 * 
	 * Note: this is another put request, but since the path param and the
	 * number of params are different, rest will be able to identify it.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param is
	 */
	@PUT
	@Path("{first}-{last}")
	@Consumes("application/xml")
	public void updateCustomer(@PathParam("first") String firstName, @PathParam("last") String lastName,
			InputStream is) {
		Customer update = readCustomer(is);
		Customer current = JDBCHandler.getUserByName(firstName, lastName);
		if (current == null)
			throw new WebApplicationException(Response.Status.NOT_FOUND);

		current.setFirstName(update.getFirstName());
		current.setLastName(update.getLastName());
		current.setStreet(update.getStreet());
		current.setState(update.getState());
		current.setZip(update.getZip());
		current.setCountry(update.getCountry());
	}

	protected void outputCustomer(OutputStream os, Customer cust) throws IOException {
		PrintStream writer = new PrintStream(os);
		writer.println("<customer id=\"" + cust.getId() + "\">");
		writer.println("   <first-name>" + cust.getFirstName() + "</first-name>");
		writer.println("   <last-name>" + cust.getLastName() + "</last-name>");
		writer.println("   <street>" + cust.getStreet() + "</street>");
		writer.println("   <city>" + cust.getCity() + "</city>");
		writer.println("   <state>" + cust.getState() + "</state>");
		writer.println("   <zip>" + cust.getZip() + "</zip>");
		writer.println("   <country>" + cust.getCountry() + "</country>");
		writer.println("</customer>");
	}

	protected Customer readCustomer(InputStream is) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(is);
			Element root = doc.getDocumentElement();
			Customer cust = new Customer();
			if (root.getAttribute("id") != null && !root.getAttribute("id").trim().equals(""))
				cust.setId(Integer.valueOf(root.getAttribute("id")));
			NodeList nodes = root.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				Node element = (Node) nodes.item(i);
				if (element.getNodeName().equals("first-name")) {
					cust.setFirstName(element.getTextContent());
				} else if (element.getNodeName().equals("last-name")) {
					cust.setLastName(element.getTextContent());
				} else if (element.getNodeName().equals("street")) {
					cust.setStreet(element.getTextContent());
				} else if (element.getNodeName().equals("city")) {
					cust.setCity(element.getTextContent());
				} else if (element.getNodeName().equals("state")) {
					cust.setState(element.getTextContent());
				} else if (element.getNodeName().equals("zip")) {
					cust.setZip(element.getTextContent());
				} else if (element.getNodeName().equals("country")) {
					cust.setCountry(element.getTextContent());
				}
			}
			return cust;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
		}
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String testServer() {
		return "<p>" + this.getClass().getSimpleName() + "<p>";
	}

}
