package com.edu.abhi.rest.filters.server;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

import com.edu.abhi.rest.filters.AnnotationToApplyFilter;

/**
 * 
 * @author abhishekkhare
 *
 *         All the request filters shown above was implemented as post-matching
 *         filters. It means that the filters would be applied only after a
 *         suitable resource method has been selected to process the actual
 *         request i.e. after request matching happens. Request matching is the
 *         process of finding a resource method that should be executed based on
 *         the request path and other request parameters. Since post-matching
 *         request filters are invoked when a particular resource method has
 *         already been selected, such filters can not influence the resource
 *         method matching process.
 * 
 *         To overcome the above described limitation, there is a possibility to
 *         mark a server request filter as a pre-matching filter, i.e. to
 *         annotate the filter class with the @PreMatching annotation.
 *         Pre-matching filters are request filters that are executed before the
 *         request matching is started. Thanks to this, pre-matching request
 *         filters have the possibility to influence which method will be
 *         matched
 */
@Provider
@PreMatching
@AnnotationToApplyFilter
public class PreGlobalFilter implements ContainerRequestFilter {
	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		System.out.println("PreGlobalFilter::DATE::" + request.getDate() + " METHOD::" + request.getMethod());

	}

}
