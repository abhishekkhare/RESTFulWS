package com.edu.abhi.rest.responseservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.edu.abhi.rest.dao.JDBCHandler;
import com.edu.abhi.rest.domain.Customer;

/**
 * 
 * @author abhishekkhare
 *
 */
@Path("/TextBookService")
public class TextBookService {
	/**
	 * http://localhost:8080/RESTFulWS/rest/TextBookService/restfuljava
	 * @return
	 */
	@GET
	@Path("/restfuljava")
	@Produces("text/plain")
	public Response getBook() {
		String book = "Inside My Mind";
		ResponseBuilder builder = Response.ok(book);
		builder.language("fr").header("Some-Header", "some value");
		return builder.build();
	}

	/**
	 * http://localhost:8080/RESTFulWS/rest/TextBookService/cookie
	 * 
	 * @return
	 */
	@GET
	@Path("/cookie")
	public Response get() {
		NewCookie[] cookie = new NewCookie[] { new NewCookie("Abhi", "khare") };
		ResponseBuilder builder = Response.ok("hello", "text/plain");
		return builder.cookie(cookie).build();
	}

	/**
	 * http://localhost:8080/RESTFulWS/rest/TextBookService/CustList
	 * @return
	 */
	@GET
	@Path("CustList")
	@Produces("application/xml")
	public Response getCustomerList() {
		List<Customer> list = new ArrayList<Customer>();
		Map<Integer, Customer> custMap = JDBCHandler.getCustomerDB();
		Set<Integer> keys = custMap.keySet();
		for (Integer key : keys) {
			list.add(custMap.get(key));
		}
		GenericEntity<List<Customer>> entity = new GenericEntity<List<Customer>>(list) {
		};
		return Response.ok(entity).build();
	}

}
