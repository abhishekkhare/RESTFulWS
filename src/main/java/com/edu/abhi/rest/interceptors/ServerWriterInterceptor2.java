package com.edu.abhi.rest.interceptors;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

@Priority(value = 2000)
@Provider
public class ServerWriterInterceptor2 implements WriterInterceptor {

	@Override
	public void aroundWriteTo(WriterInterceptorContext interceptorContext) throws IOException, WebApplicationException {
		System.out.println("ServerWriterInterceptor2 invoked.");
		OutputStream outputStream = interceptorContext.getOutputStream();
		String responseContent = "\nResponse changed in ServerWriterInterceptor2.";
		outputStream.write(responseContent.getBytes());
		interceptorContext.setOutputStream(outputStream);

		interceptorContext.proceed();
	}
}
