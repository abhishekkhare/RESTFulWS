package com.edu.abhi.rest.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalResponseFilter implements ContainerResponseFilter {
	public void filter(ContainerRequestContext req, ContainerResponseContext res) throws IOException {
		System.out.println("GlobalResponseFilter Start");
		if (req.getMethod().equals("GET")) {
			req.getHeaders().add("max-age", "100");
			System.out.println("GlobalResponseFilter::GET::STATUS::" + res.getStatus() );
		}else{
			System.out.println("GlobalResponseFilter::NOT GET::STATUS::" + res.getStatus() );
			req.getHeaders().add("max-age", "1000");
		}
		System.out.println("GlobalResponseFilter End");
	}

}
