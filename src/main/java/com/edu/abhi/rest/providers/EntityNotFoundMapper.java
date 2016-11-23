package com.edu.abhi.rest.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.edu.abhi.rest.domain.EntityNotFoundException;
/**
 * 
 * @author abhishekkhare
 *
 */
@Provider
public class EntityNotFoundMapper implements ExceptionMapper<EntityNotFoundException> {
	public Response toResponse(EntityNotFoundException e) {
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
