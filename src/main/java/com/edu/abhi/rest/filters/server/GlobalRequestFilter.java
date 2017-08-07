package com.edu.abhi.rest.filters.server;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 * 
 * @author abhishekkhare
 *
 */
@Provider
@PreMatching
public class GlobalRequestFilter implements ContainerRequestFilter {
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("GlobalRequestFilter Start");
		System.out.println("GlobalRequestFilter:::" + requestContext.getDate());
		System.out.println("GlobalRequestFilter End");
	}

}
