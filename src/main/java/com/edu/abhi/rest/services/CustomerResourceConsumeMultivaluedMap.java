package com.edu.abhi.rest.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import com.edu.abhi.rest.dao.JDBCHandler;
import com.edu.abhi.rest.domain.Customer;


/**
 * 
 * @author abhishekkhare
 *
 */
@Path("/CustomerResourceConsumeMultivaluedMap")
public class CustomerResourceConsumeMultivaluedMap {
	public CustomerResourceConsumeMultivaluedMap() {
	}

	/**
	 * http://localhost:8080/RESTFulWS/rest/CustomerResourceConsumeMultivaluedMap
	 * 
	 * @param form
	 * @return
	 */
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/x-www-form-urlencoded")
	public MultivaluedMap<String,String> post(MultivaluedMap<String, String> form) {
		Customer cust = new Customer();
		MultivaluedMap<String,String> responseForm = new MultivaluedHashMap<String,String>();
			try{
				cust.setId(JDBCHandler.getIdCounter().incrementAndGet());
				cust.setFirstName(form.get("firstname").get(0));
				cust.setLastName(form.get("lastname").get(0));
				List<String> rsList = new ArrayList<String>();
				rsList.add(cust.toString());
				responseForm.put("response", rsList);
			}catch(NullPointerException e){
				List<String> rsList = new ArrayList<String>();
				rsList.add("Invalid Input");
				responseForm.put("response", rsList);
			}
			
		
			return responseForm;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String testServer() {
		return "<p>" + this.getClass().getSimpleName() + "<p>";
	}
}
