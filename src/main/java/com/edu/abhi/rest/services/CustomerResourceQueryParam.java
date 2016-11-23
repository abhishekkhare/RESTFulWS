package com.edu.abhi.rest.services;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;

import com.edu.abhi.rest.dao.JDBCHandler;
import com.edu.abhi.rest.domain.Customer;

/**
 * 
 * @author abhishekkhare
 * 
 * 
 *
 */
@Path("/CustomerResourceQueryParam")
public class CustomerResourceQueryParam {

	public CustomerResourceQueryParam() {
	}
	
	/**
	 * In this case @DefaultValue value of 2 will be used for size
	 * URL - http://localhost:8080/RESTFulWS/rest/CustomerResourceQueryParam?start=1
	 * 
	 * In this case both start and size are provided as query parameter.
	 * URL - http://localhost:8080/RESTFulWS/rest/CustomerResourceQueryParam?start=1&size=4
	 * 
	 * @param start
	 * @param size
	 * @return
	 */
	@GET
	@Produces("application/xml")
	public StreamingOutput getCustomers(final @QueryParam("start") int start,
			final @QueryParam("size") @DefaultValue("2") int size) {
		return new StreamingOutput() {
			public void write(OutputStream outputStream) throws IOException, WebApplicationException {
				PrintStream writer = new PrintStream(outputStream);
				writer.println("<customers>");
				synchronized (JDBCHandler.getCustomerDB()) {
					int i = 0;
					for (Customer customer : JDBCHandler.getCustomerDB().values()) {
						if (i >= start && i < start + size)
							outputCustomer("   ", writer, customer);
						i++;
					}
				}
				writer.println("</customers>");
			}
		};
	}

	/**
	 * In this case @DefaultValue value of 2 will be used for size
	 * URL - http://localhost:8080/RESTFulWS/rest/CustomerResourceQueryParam/uriinfo?start=1
	 * 
	 * In this case both start and size are provided as query parameter.
	 * URL - http://localhost:8080/RESTFulWS/rest/CustomerResourceQueryParam/uriinfo?start=1&size=4
	 * 
	 * @param info
	 * @return
	 */
	@GET
	@Produces("application/xml")
	@Path("uriinfo")
	public StreamingOutput getCustomers(@Context UriInfo info) {
		int start = 0;
		int size = 2;
		if (info.getQueryParameters().containsKey("start")) {
			start = Integer.valueOf(info.getQueryParameters().getFirst("start"));
		}
		if (info.getQueryParameters().containsKey("size")) {
			size = Integer.valueOf(info.getQueryParameters().getFirst("size"));
		}
		return getCustomers(start, size);
	}

	
	private void outputCustomer(String indent, PrintStream writer, Customer cust) throws IOException {
		writer.println(indent + "<customer id=\"" + cust.getId() + "\">");
		writer.println(indent + "   <first-name>" + cust.getFirstName() + "</first-name>");
		writer.println(indent + "   <last-name>" + cust.getLastName() + "</last-name>");
		writer.println(indent + "   <street>" + cust.getStreet() + "</street>");
		writer.println(indent + "   <city>" + cust.getCity() + "</city>");
		writer.println(indent + "   <state>" + cust.getState() + "</state>");
		writer.println(indent + "   <zip>" + cust.getZip() + "</zip>");
		writer.println(indent + "   <country>" + cust.getCountry() + "</country>");
		writer.println(indent + "</customer>");
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String testServer() {
		return "<p>" + this.getClass().getSimpleName() + "<p>";
	}

}
