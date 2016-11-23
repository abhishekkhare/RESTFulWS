package com.edu.abhi.rest.responseservice;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.edu.abhi.rest.dao.JDBCHandler;
import com.edu.abhi.rest.domain.Customer;
import com.edu.abhi.rest.domain.EntityNotFoundException;


/**
 * 
 * @author abhishekkhare
 *
 */
@Path("ExceptionHandlingService")
public class ExceptionHandlingService {
	/**
	 * http://localhost:8080/RESTFulWS/rest/ExceptionHandlingService/10001
	 * http://localhost:8080/RESTFulWS/rest/ExceptionHandlingService/1
	 * @param id
	 * @return
	 * @throws EntityNotFoundException
	 */
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Customer getCustomer(@PathParam("id") int id) throws EntityNotFoundException {
		Customer cust = JDBCHandler.getCustomerDB().get(id);
		if (cust == null) {
			throw new EntityNotFoundException();
		}
		return cust;
	}

	/**
	 * http://localhost:8080/RESTFulWS/rest/ExceptionHandlingService/notfound/1
	 * http://localhost:8080/RESTFulWS/rest/ExceptionHandlingService/notfound/10001
	 * @param id
	 * @return
	 */
	@GET
	@Path("notfound/{id}")
	@Produces("application/xml")
	public Customer getCustomer1(@PathParam("id") int id) {
		Customer cust = JDBCHandler.getCustomerDB().get(id);
		if (cust == null) {
			throw new NotFoundException();
		}
		return cust;
	}

	/**
	 * http://localhost:8080/RESTFulWS/rest/ExceptionHandlingService/badrq/10001
	 * http://localhost:8080/RESTFulWS/rest/ExceptionHandlingService/badrq/1
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("badrq/{id}")
	@Produces("application/xml")
	public Customer getCustomer2(@PathParam("id") int id) {
		Customer cust = JDBCHandler.getCustomerDB().get(id);
		if (cust == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		return cust;
	}
}
