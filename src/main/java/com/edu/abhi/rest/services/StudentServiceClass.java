package com.edu.abhi.rest.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.edu.abhi.rest.domain.Address;
import com.edu.abhi.rest.domain.Student;

/**
 * 
 * Method can @Produce and @Consume multiple MIME Type.
 * 
 * @author abhishekkhare
 *
 */
@Path("/Student")
public class StudentServiceClass {
	public static final String DATA_STORE = "StudentDATA.txt";

	/**
	 * URL::: http://localhost:8080/RESTFulWS/rest/Student/
	 * 
	 * 
	 * INPUT:::: <?xml version="1.0" encoding="UTF-8"?>
	 * <student id="1"> <firstname>Duke</firstname> <lastname>OfJava</lastname>
	 * <address> <number>1</number> <street>Duke's Way</street>
	 * <city>JavaTown</city> <state>JA</state> <zip>12345</zip>
	 * <country>USA</country> </address> <email>duke@example.com</email>
	 * <phone>123-456-7890</phone> </student> OR
	 * 
	 * 
	 * INPUT::: { "id": "2", "firstname": "Duke2", "lastname": "OfJava",
	 * "address": { "number": 2, "street": "Duke2's Way", "city": "Java2Town",
	 * "state": "JA", "zip": "12345", "country": "USA" }, "email":
	 * "duke2@example.com", "phone": "122-456-7890" }
	 * 
	 * @param student
	 * @return
	 */
	@POST
	@Consumes({ "application/xml", "application/json" })
	public Response createCustomer(Student student) {
		try {
			long studentId = persist(student);
			return Response.created(URI.create("/" + studentId)).build();
		} catch (Exception e) {
			throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * URL::: http://localhost:8080/RESTFulWS/rest/Student/2
	 * 
	 * @param studentId
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces({ "application/xml", "application/json" })
	public Student getStudent(@PathParam("id") String studentId) {
		Student student = null;

		try {
			student = findById(studentId);
		} catch (Exception ex) {
			System.out.println("Error calling searchStudent() for studentId {0}. {1}"
					+ new Object[] { studentId, ex.getMessage() });
		}
		return student;
	}

	private Student findById(String studentId) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(DATA_STORE));
		String rawData = properties.getProperty(studentId);

		if (rawData != null) {
			final String[] field = rawData.split(",");

			Address address = new Address();
			Student student = new Student();
			student.setId(Integer.parseInt(studentId));
			student.setAddress(address);

			student.setFirstname(field[0]);
			student.setLastname(field[1]);
			address.setNumber(Integer.parseInt(field[2]));
			address.setStreet(field[3]);
			address.setCity(field[4]);
			address.setState(field[5]);
			address.setZip(field[6]);
			address.setCountry(field[7]);
			student.setEmail(field[8]);
			student.setPhone(field[9]);

			return student;
		}
		return null;
	}

	private long persist(Student student) throws IOException {

		File dataFile = new File(DATA_STORE);

		if (!dataFile.exists()) {
			dataFile.createNewFile();
		}

		long studentId = student.getId();
		Address address = student.getAddress();

		Properties properties = new Properties();
		properties.load(new FileInputStream(dataFile));

		properties.setProperty(String.valueOf(studentId),
				student.getFirstname() + "," + student.getLastname() + "," + address.getNumber() + ","
						+ address.getStreet() + "," + address.getCity() + "," + address.getState() + ","
						+ address.getZip() + "," + address.getCountry() + "," + student.getEmail() + ","
						+ student.getPhone());

		properties.store(new FileOutputStream(DATA_STORE), null);

		return studentId;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String testServer() {
		return "<p>" + this.getClass().getSimpleName() + "<p>";
	}

}
