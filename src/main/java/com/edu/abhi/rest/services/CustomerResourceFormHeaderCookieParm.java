package com.edu.abhi.rest.services;

import java.net.URI;
import java.text.DateFormat;
import java.util.Date;

import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.edu.abhi.rest.dao.JDBCHandler;
import com.edu.abhi.rest.domain.Customer;

/**
 * 
 * @author abhishekkhare
 * 
 * 
 *
 */
@Path("/CustomerResourceFormHeaderCookieParm")
public class CustomerResourceFormHeaderCookieParm {

	public CustomerResourceFormHeaderCookieParm() {
	}

	/**
	 * http://localhost:8080/RESTFulWS/rest/CustomerResourceFormHeaderCookieParm
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	@POST
	@Produces("text/html")
	public Response createCustomer(@FormParam("firstname") String first, @FormParam("lastname") String last) {
		Customer customer = new Customer();
		customer.setId(JDBCHandler.getIdCounter().incrementAndGet());
		customer.setFirstName(first);
		customer.setLastName(last);
		JDBCHandler.getCustomerDB().put(customer.getId(), customer);
		System.out.println("Created customer " + customer.getId());
		String output = "Created customer <a href=\"CustomerResourceFormHeaderCookieParm/" + customer.getId() + "\">" + customer.getId() + "</a>";
		String lastVisit = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG).format(new Date());
		URI location = URI.create("/CustomerResourceFormHeaderCookieParm/" + customer.getId());
		return Response.created(location).entity(output).cookie(new NewCookie("last-visit", lastVisit)).build();

	}

	/**
	 * http://localhost:8080/RESTFulWS/rest/CustomerResourceFormHeaderCookieParm/1
	 * 
	 * 
	 * @param id
	 * @param userAgent
	 * @param date
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces("text/plain")
	public Response getCustomer(@PathParam("id") int id, @HeaderParam("User-Agent") String userAgent,
			@CookieParam("last-visit") String date) {
		final Customer customer = JDBCHandler.getCustomerDB().get(id);
		if (customer == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		String output = "User-Agent: " + userAgent + "\r\n";
		output += "Last visit: " + date + "\r\n\r\n";
		output += "Customer: " + customer.getFirstName() + " " + customer.getLastName();
		String lastVisit = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG).format(new Date());
		return Response.ok(output).cookie(new NewCookie("last-visit", lastVisit)).build();
	}

	/**
	 * http://localhost:8080/RESTFulWS/rest/CustomerResourceFormHeaderCookieParm/1
	 * Header Input -  AppName - AbhiTest
	 * @param id
	 * @param AppName
	 * @param date
	 * @return
	 */
	@POST
	@Path("{id}")
	@Produces("text/plain")
	public Response getCustomerHeaderParam(@PathParam("id") int id, @HeaderParam("AppName") String AppName,
			@CookieParam("last-visit") String date) {
		final Customer customer = JDBCHandler.getCustomerDB().get(id);
		if (customer == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		String output = "Application-Name: " + AppName + "\r\n";
		output += "Last visit: " + date + "\r\n\r\n";
		output += "Customer: " + customer.getFirstName() + " " + customer.getLastName();
		String lastVisit = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG).format(new Date());
		return Response.ok(output).cookie(new NewCookie("last-visit", lastVisit)).build();
	}
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String testServer() {
		return "<p>" + this.getClass().getSimpleName() + "<p>";
	}

}
