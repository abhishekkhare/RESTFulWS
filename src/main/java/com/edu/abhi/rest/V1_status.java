package com.edu.abhi.rest;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.edu.abhi.rest.dao.JDBCHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author abhishekkhare
 *
 */
@Path("/v1/status")
public class V1_status {
	private static final String api_version = "00.02.00"; //version of the api
	/**
	 * This method sits at the root of the api.  It will return the name
	 * of this api.
	 * 
	 * @return String - Title of the api
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p>";
	}
	
	/**
	 * This method will return the version number of the api
	 * Note: this is nested one down from the root.  You will need to add version
	 * into the URL path.
	 * 
	 * Example:
	 * http://localhost:7001/com.youtube.rest/api/v1/status/version
	 * 
	 * @return String - version number of the api
	 */
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version:</p>" + api_version;
	}
	
	/**
	 * This method will connect to the oracle database and return the date/time stamp.
	 * It will then return the date/time to the user in String format
	 * 
	 * This was explained in Part 3 of the Java Rest Tutorial Series on YouTube
	 * 
	 * Pre-episode 6, updated because Oracle308tube.java no longer accessible.
	 * 
	 * @return String -  returns the database date/time stamp
	 * @throws Exception
	 */
	@Path("/database")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String returnDatabaseStatus() throws Exception {
		JDBCHandler jdbcHandler =  new JDBCHandler();
		Map<Integer, String> map = jdbcHandler.getTeam();
		return getJSON(map);
	}
	
	public String getJSON(Object o) {
		// Get Gson object
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// create JSON String from Object
		String jsonString = gson.toJson(o);
		System.out.print(jsonString);
		return jsonString;
	}
}
