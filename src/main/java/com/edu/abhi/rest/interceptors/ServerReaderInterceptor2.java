package com.edu.abhi.rest.interceptors;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

@Priority(value = 2000)
@Provider
@AnnotationToApplyInterceptor
public class ServerReaderInterceptor2 implements ReaderInterceptor {

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext interceptorContext)
			throws IOException, WebApplicationException {
		System.out.println("ServerReaderInterceptor2 invoked.");
		InputStream inputStream = interceptorContext.getInputStream();
		byte[] bytes = new byte[inputStream.available()];
		inputStream.read(bytes);
		String requestContent = new String(bytes);
		requestContent = requestContent + "\nRequest changed in ServerReaderInterceptor2.";
		interceptorContext.setInputStream(new ByteArrayInputStream(requestContent.getBytes()));
		return interceptorContext.proceed();
	}
}
