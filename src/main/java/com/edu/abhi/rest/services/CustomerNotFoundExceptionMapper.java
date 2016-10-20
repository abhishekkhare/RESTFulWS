package com.edu.abhi.rest.services;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 
 * @author abhishekkhare
 *
 */
@Provider
public class CustomerNotFoundExceptionMapper implements ExceptionMapper<CustomerNotFoundException> {
	public Response toResponse(CustomerNotFoundException exception) {
		return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).type("text/plain").build();
	}
}
