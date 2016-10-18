package com.edu.abhi.rest.services;

import java.net.URI;

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

import com.edu.abhi.rest.dao.JDBCHandler;
import com.edu.abhi.rest.domain.Customer;
/**
 * 
 * @author abhishekkhare
 *
 */
@Path("/CustomerResourceConsume1")
public class CustomerResourceConsume1 {
	public CustomerResourceConsume1() {
	}

	/**
	 * URL - http://localhost:8080/RESTFullWS/rest/CustomerResourceConsume1
	 * 
	 * XML - <?xml version="1.0" encoding="UTF-8" standalone="yes"?><customer><city>Sunny</city><country>USA</country><firstName>Abhishek</firstName><lastName>Khare</lastName><state>CA</state><street>ABC Drive</street><zip>02115</zip></customer>
	 * 
	 * The input XML will automatically be converted into the Customer Object
	 * 
	 * @param customer
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createCustomer(Customer customer) {
		customer.setId(JDBCHandler.getIdCounter().incrementAndGet());
		JDBCHandler.getCustomerDB().put(customer.getId(), customer);
		System.out.println("Created customer " + customer.getId());
		return Response.created(URI.create("/customers/" + customer.getId())).build();

	}

	/**
	 * URL - http://localhost:8080/RESTFullWS/rest/CustomerResourceConsume1/1
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Customer getCustomer(@PathParam("id") int id) {
		Customer customer = JDBCHandler.getCustomerDB().get(id);
		if (customer == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return customer;
	}

	/**
	 * URL - http://localhost:8080/RESTFullWS/rest/CustomerResourceConsume1/1
	 * 
	 * The input XML will automatically be converted into the Customer Object
	 * @param id
	 * @param update
	 */
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public void updateCustomer(@PathParam("id") int id, Customer update) {
		Customer current = JDBCHandler.getCustomerDB().get(id);
		if (current == null)
			throw new WebApplicationException(Response.Status.NOT_FOUND);

		current.setFirstName(update.getFirstName());
		current.setLastName(update.getLastName());
		current.setStreet(update.getStreet());
		current.setState(update.getState());
		current.setZip(update.getZip());
		current.setCountry(update.getCountry());
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String testServer() {
		return "<p>" + this.getClass().getSimpleName() + "<p>";
	}
}
