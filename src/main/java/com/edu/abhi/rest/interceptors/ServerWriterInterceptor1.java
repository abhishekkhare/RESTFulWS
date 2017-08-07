package com.edu.abhi.rest.interceptors;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

@Priority(value = 1000)
@Provider
public class ServerWriterInterceptor1 implements WriterInterceptor {

	@Override
	public void aroundWriteTo(WriterInterceptorContext interceptorContext) throws IOException, WebApplicationException {
		System.out.println("ServerWriterInterceptor1 invoked");
		OutputStream outputStream = interceptorContext.getOutputStream();
		String responseContent = "\nResponse changed in ServerWriterInterceptor1.";
		outputStream.write(responseContent.getBytes());
		interceptorContext.setOutputStream(outputStream);

		interceptorContext.proceed();
	}
}
