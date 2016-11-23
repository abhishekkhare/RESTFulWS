package com.edu.abhi.rest.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * 
 * @author abhishekkhare
 *
 */


@Path("/files")
public class FileUploadDownloadService {
	private static String basePath = "/Users/abhishekkhare/Documents/";
	private static String FILE_PATH ="" ;
	/**
	 * http://localhost:8080/RESTFulWS/rest/files/upload
	 * 
	 * Use the Upload.html to upload.
	 *
	 */
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_HTML)
	public String uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		String FILE_PATH = basePath + fileDetail.getFileName();

		// save it
		writeToFile(uploadedInputStream, FILE_PATH);

		String output = "File uploaded to : " + FILE_PATH;

		return "<p>" + output + "<p>";

	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
		try {
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * http://localhost:8080/RESTFulWS/rest/files/download/abc.png
	 * @param fileName
	 * @return
	 */
	@GET
	@Path("/download/{fileName}")
	@Produces("text/plain")
	
	public Response getFile(@PathParam("fileName")  String fileName) {
		FILE_PATH=basePath+fileName;
		System.out.println("FILE_PATH:" + FILE_PATH);
		if(FILE_PATH!=null){
			File file = new File(FILE_PATH);

			ResponseBuilder response = Response.ok((Object) file);
			response.header("Content-Disposition", "attachment; filename=\"file_from_server.log\"");
			return response.build();	
		}
		throw new RuntimeException("Requested File Was not found");

	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String testServer() {
		return "<p>" + this.getClass().getSimpleName() + "<p>";
	}

}
