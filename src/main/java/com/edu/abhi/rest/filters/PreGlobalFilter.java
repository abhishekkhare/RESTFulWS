package com.edu.abhi.rest.filters;

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
public class PreGlobalFilter implements ContainerRequestFilter{
	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		System.out.println("PreGlobalFilter::DATE::" + request.getDate()  + " METHOD::" + request.getMethod());
		
	}

}
