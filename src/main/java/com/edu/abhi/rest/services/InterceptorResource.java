package com.edu.abhi.rest.services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.edu.abhi.rest.interceptors.AnnotationToApplyInterceptor;

/**
 * http://localhost:8080/RESTFulWS/rest/interceptor
 * inputMessage: This is test string "Abhishek"
 * @author abhishekkhare
 *
 */
@Path("/interceptor")
public class InterceptorResource {

	@POST
	@AnnotationToApplyInterceptor
	public Response testInterceptor(String inputMessage) {
		System.out.println("Input Message: " + inputMessage);

		Response response = Response.ok("\nOrder successfully placed").status(Status.OK).build();

		return response;
	}
}
