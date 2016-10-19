package com.edu.abhi.rest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.edu.abhi.rest.dao.JDBCHandler;
import com.edu.abhi.rest.domain.Product;

/**
 * 
 * @author abhishekkhare
 * 
 * Note only JAXB classes that have @XmlRootElement can be converted directly from POJO to XML or JSON.
 *
 */
@Path("/ProductServiceJAXBXml")
public class ProductServiceJAXBXml {
	/**
	 * URL:: http://localhost:8080/RESTFulWS/rest/ProductServiceJAXBXml/getJ
	 * 
	 * 
	 * RESPONSE::: {"description": "Queen size mattress", "id": 1, "name":
	 * "Mattress", "price": 500 }
	 * 
	 * @return
	 */
	@GET
	@Path("/getJ")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProductJ() {
		Product product = new Product();
		product.setId(1);
		product.setName("Mattress");
		product.setDescription("Queen size mattress");
		product.setPrice(500);
		return product;
	}

	/**
	 * URL::http://localhost:8080/RESTFulWS/rest/ProductServiceJAXBXml/getX
	 * 
	 * 
	 * RESPONSE:::<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	 * <product> <id>1</id> <name>Mattress</name> <description>Queen size
	 * mattress</description> <price>500</price> </product>
	 * 
	 * @return
	 */
	@GET
	@Path("/getX")
	@Produces(MediaType.APPLICATION_XML)
	public Product getProductX() {
		Product product = new Product();
		product.setId(1);
		product.setName("Mattress");
		product.setDescription("Queen size mattress");
		product.setPrice(500);
		return product;
	}

	/**
	 * URL::: http://localhost:8080/RESTFulWS/rest/ProductServiceJAXBXml/createX
	 * 
	 * INPURT::: <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	 * <product> <id>3</id> <name>Mattress</name> <description>Queen size
	 * mattress</description> <price>500</price> </product>
	 * 
	 * RESPONSE:::
	 * <p>
	 * /ProductServiceJAXBXml/3
	 * </p>
	 * 
	 * @param product
	 * @return
	 */
	@POST
	@Path("/createX")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_HTML)
	public String createProductX(Product product) {
		JDBCHandler.getProductDB().put(product.getId(), product);
		// return Response.created(URI.create("/ProductServiceJAXBXml/" +
		// product.getId())).build();
		return "<p>" + "/ProductServiceJAXBXml/" + product.getId() + "</p>";
	}

	/**
	 * URL::: http://localhost:8080/RESTFulWS/rest/ProductServiceJAXBXml/createJ
	 * 
	 * INPUT::: {"description": "Queen size mattress", "id": 4, "name":
	 * "Mattress", "price": 500 }
	 * 
	 * RESPONSE:::
	 * <p>
	 * /ProductServiceJAXBXml/4
	 * </p>
	 * 
	 * @param product
	 * @return
	 */
	@POST
	@Path("/createJ")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String createProductJ(Product product) {
		JDBCHandler.getProductDB().put(product.getId(), product);
		// return Response.created(URI.create("/ProductServiceJAXBXml/" +
		// product.getId())).build();
		return "<p>" + "/ProductServiceJAXBXml/" + product.getId() + "</p>";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String testServer() {
		return "<p>" + this.getClass().getSimpleName() + "<p>";
	}
}