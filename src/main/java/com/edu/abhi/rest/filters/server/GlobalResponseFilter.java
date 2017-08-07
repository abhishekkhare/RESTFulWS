package com.edu.abhi.rest.filters.server;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * 
 * @author abhishekkhare
 *
 *         The filter must inherit from the ContainerResponseFilter and must be
 *         registered as a provider. The filter will be executed for every
 *         response which is in most cases after the resource method is
 *         executed. Response filters are executed even if the resource method
 *         is not run, for example when the resource method is not found and 404
 *         "Not found" response code is returned by the Jersey runtime. In this
 *         case the filter will be executed and will process the 404 response.
 */
@Provider
public class GlobalResponseFilter implements ContainerResponseFilter {
	@Override
	public void filter(ContainerRequestContext req, ContainerResponseContext res) throws IOException {
		System.out.println("GlobalResponseFilter Start");
		if (req.getMethod().equals("GET")) {
			req.getHeaders().add("max-age", "100");
			System.out.println("GlobalResponseFilter::GET::STATUS::" + res.getStatus());
		} else {
			System.out.println("GlobalResponseFilter::NOT GET::STATUS::" + res.getStatus());
			req.getHeaders().add("max-age", "1000");
		}
		System.out.println("GlobalResponseFilter End");
	}

}
