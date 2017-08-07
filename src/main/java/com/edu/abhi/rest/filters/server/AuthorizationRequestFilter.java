package com.edu.abhi.rest.filters.server;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

/**
 * 
 * @author abhishekkhare
 * The request filter is similar to the response filter but does not have access to the ContainerResponseContext as no response is accessible yet.
 * The filter has possibility to manipulate the request parameters including request headers or entity.
 * 
 */

@Provider
public class AuthorizationRequestFilter implements ContainerRequestFilter {
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("AuthorizationRequestFilter Start");
//		final SecurityContext securityContext = requestContext.getSecurityContext();
//		
//		if (securityContext == null || !securityContext.isUserInRole("privileged")) {
//			System.out.println("AuthorizationRequestFilter:::" + securityContext);
//			requestContext.abortWith(
//					Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access the resource.").build());
//		}
		
		System.out.println("AuthorizationRequestFilter End");
	}
}
