package com.edu.abhi.rest.conneg;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.edu.abhi.rest.dao.JDBCHandler;
import com.edu.abhi.rest.domain.Customer;

/**
 * http://localhost:8080/RESTFulWS/rest/CustomerResourceConneg/1
 * 
 * @author abhishekkhare
 *
 */
@Path("/CustomerResourceConneg")
public class CustomerResourceConneg {
	/**
	 * Accept:application/xml
	 * 
	 * or try
	 * 
	 * Accept:text/*,application/xml
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Customer getCustomerXml(@PathParam("id") int id) {
		Customer customer = JDBCHandler.getCustomerDB().get(id);
		if (customer == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return customer;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces("text/plain")
	public String getCustomerText(@PathParam("id") int id) {
		Customer customer = JDBCHandler.getCustomerDB().get(id);
		if (customer == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return customer.toString();
	}

	/**
	 * Accept:application/json
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Customer getCustomerJson(@PathParam("id") int id) {
		Customer customer = JDBCHandler.getCustomerDB().get(id);
		if (customer == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return customer;
	}

	/**
	 * http://localhost:8080/RESTFulWS/rest/CustomerResourceConneg
	 * 
	 * Accept : application/json or try Accept : application/xml
	 * 
	 * @return
	 */
	@GET
	@Path("/test")
	@Produces({ "application/xml", "application/json" })
	public Response getCustomers() {
		System.out.println("getCustomers start");
		List<Customer> list = new ArrayList<Customer>();
		Map<Integer, Customer> custMap = JDBCHandler.getCustomerDB();
		Set<Integer> keys = custMap.keySet();
		for (Integer key : keys) {
			list.add(custMap.get(key));
		}
		GenericEntity<List<Customer>> entity = new GenericEntity<List<Customer>>(list) {
		};
		System.out.println("getCustomers end");
		return Response.ok(entity).build();
	}

	@GET
	@Path("/get")
	public Response get(@Context HttpHeaders headers) {
		MediaType type = headers.getAcceptableMediaTypes().get(0);
		Locale language = headers.getAcceptableLanguages().get(0);
		String responseObject = type.toString() + language.toString();
		System.out.println(responseObject);
		Response.ResponseBuilder builder = Response.ok(responseObject, type);
		builder.language(language);
		return builder.build();
	}
}
