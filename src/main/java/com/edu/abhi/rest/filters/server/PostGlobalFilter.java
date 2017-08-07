package com.edu.abhi.rest.filters.server;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class PostGlobalFilter implements ContainerRequestFilter {
	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		System.out.println("PostGlobalFilter::DATE::" + request.getDate() + " METHOD::" + request.getMethod());

	}

}
