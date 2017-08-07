package com.edu.abhi.rest.services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/interceptor")
public class InterceptorResource {

	@POST
	public Response testInterceptor(String inputMessage) {
		System.out.println("Input Message: " + inputMessage);

		Response response = Response.ok("\nOrder successfully placed").status(Status.OK).build();

		return response;
	}
}
