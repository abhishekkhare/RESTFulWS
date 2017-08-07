package com.edu.abhi.rest.services;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.edu.abhi.rest.domain.Customer;
/**
 * Note its the "CustomerNotFoundExceptionMapper" that converts the exception to a response object.
 * 
 * @author abhishekkhare
 *
 */

@Path("/complexresponse")
public class ComplexResponse {
	/**
	 * http://localhost:8080/RESTFulWS/rest/complexresponse/created
	 * 
	 * @return
	 */
	@Path("/created")
	@POST
	@Consumes("application/xml")
	public Response createCustomer201() {
		System.out.println("Created customer 201 ");
		return Response.created(URI.create("/customers/" + 12345)).build();

	}

	/**
	 * http://localhost:8080/RESTFulWS/rest/complexresponse/ok
	 * 
	 * @return
	 */
	@Path("/ok")
	@POST
	@Consumes("application/xml")
	public Response createCustomer200() {
		System.out.println("Created customer 200");
		return Response.ok().build();

	}
	
	/**
	 * http://localhost:8080/RESTFulWS/rest/complexresponse/nocontent
	 * 
	 * 
	 * @return
	 */
	@Path("/nocontent")
	@POST
	@Consumes("application/xml")
	public Response createCustomer204() {
		System.out.println("Created customer 204");
		return Response.noContent().build();

	}
	
	
	/**
	 * http://localhost:8080/RESTFulWS/rest/complexresponse/get1
	 * 
	 * @return
	 */
	@Path("/get1")
	@GET
	@Produces("application/xml")
	public Customer get1() {
		throw new CustomerNotFoundException("Could not find customer ");
	}
	
	
	
	
	
}
