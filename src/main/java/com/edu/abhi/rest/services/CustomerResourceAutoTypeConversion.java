package com.edu.abhi.rest.services;

import java.net.URL;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.edu.abhi.rest.dao.JDBCHandler;
import com.edu.abhi.rest.domain.Customer;
import com.edu.abhi.rest.services.CarResourceMatrixAndPathParam.Color;

/**
 * 
 * @author abhishekkhare
 * To resolve the Producing media type conflict. you should add the Path annotation to conflicting methods 
 *
 */
@Path("/CustomerResourceAutoTypeConversion")
public class CustomerResourceAutoTypeConversion {
	public CustomerResourceAutoTypeConversion() {
	}

	/**
	 * http://localhost:8080/RESTFulWS/rest/CustomerResourceAutoTypeConversion/get/1
	 * @param id
	 * @return
	 */
	
	@GET
	@Path("get/{id}")
	public String get(@PathParam("id") int id) {
		final Customer customer = JDBCHandler.getCustomerDB().get(id);
		String output = "Customer: " + customer.getFirstName() + " " + customer.getLastName();
		System.out.println(output);
		return output;
	}

	/**
	 * 
	 * http://localhost:8080/RESTFulWS/rest/CustomerResourceAutoTypeConversion/url 
	 * Referer - https://www.google.com/maps
	 * @param referer
	 * @return
	 */
	@GET
	@Path("url")
	public String get(@HeaderParam("Referer") URL referer) {
		return referer.getPath();
	}

	/**
	 * http://localhost:8080/RESTFulWS/rest/CustomerResourceAutoTypeConversion/nissan/altima;color=grey
	 * @param make
	 * @param model
	 * @param color
	 * @return
	 */
	@GET
	@Path("/{make}/{model}")
	public String getPicture(@PathParam("make") String make, @PathParam("model") String model, @MatrixParam("color") Color color) {
		String output = "Model:" + model + " Make:" + make + " Color:" + color;
		System.out.println(output);
		return output;

	}

	/**
	 * http://localhost:8080/RESTFulWS/rest/CustomerResourceAutoTypeConversion/getCust?start=1&size=4&orderBy=age&orderBy=name&orderBy=ID
	 * @param start
	 * @param size
	 * @param orderBy
	 * @return
	 */
	@GET
	@Path("getCust")
	public String getCustomers(@QueryParam("start") int start, @QueryParam("size") int size, @QueryParam("orderBy") List<String> orderBy) {
		String output = "";
		output+="Start:" + start;
		output+=" Size:" + size;
		output+=" OrderBy:";
		for (String string : orderBy) {
			output+=string +",";
		}
		System.out.println(output);
		return output;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String testServer() {
		return "<p>" + this.getClass().getSimpleName() + "<p>";
	}
}
