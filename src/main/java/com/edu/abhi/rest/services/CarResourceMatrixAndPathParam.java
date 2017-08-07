package com.edu.abhi.rest.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;

@Path("/CarResourceMatrixAndPathParam")
public class CarResourceMatrixAndPathParam
{
   public static enum Color
   {
      red,
      white,
      blue,
      grey,
      black
   }

   /**
    * 
    * URL - http://localhost:8080/RESTFulWS/rest/CarResourceMatrixAndPathParam/matrix/nissan/altima/2005;color=grey
    * URL - http://localhost:8080/RESTFulWS/rest/CarResourceMatrixAndPathParam/matrix/honda/accord/2012;color=grey
    * URL - http://localhost:8080/RESTFulWS/rest/CarResourceMatrixAndPathParam/matrix/infinity/qx60/2017;color=blue
    * 
    * @param make
    * @param car
    * @param color
    * @param year
    * @return
    */
   @GET
   @Path("/matrix/{make}/{model}/{year}")
   @Produces("text/plain")
   public String getFromMatrixParam(@PathParam("make") String make,
                                    @PathParam("model") PathSegment car,
                                    @MatrixParam("color") Color color,
                                    @PathParam("year") String year)
   {
      return "A " + color + " " + year + " " + make + " " + car.getPath();
   }
   
   /**
    * 
    * URL - http://localhost:8080/RESTFulWS/rest/CarResourceMatrixAndPathParam/matrix1/nissan/altima/2005;color=grey
    * URL - http://localhost:8080/RESTFulWS/rest/CarResourceMatrixAndPathParam/matrix1/honda/accord/2012;color=grey
    * URL - http://localhost:8080/RESTFulWS/rest/CarResourceMatrixAndPathParam/matrix1/infinity/qx60/2017;color=black
    * 
    * Here we see that the MatrixParam has to be the last thing in the URL.
    * 
    * @param make
    * @param car
    * @param color
    * @param year
    * @return
    */
   @GET
   @Path("/matrix1/{make}/{model}/{year}")
   @Produces("text/plain")
   public String getFromMatrixParam(@MatrixParam("color") Color color,
		   							@PathParam("make") String make,
                                    @PathParam("model") PathSegment car,
                                    @PathParam("year") String year)
   {
      return "A " + color + " " + year + " " + make + " " + car.getPath() + " found";
   }


   /**
    * 
    * URL - http://localhost:8080/RESTFulWS/rest/CarResourceMatrixAndPathParam/segment/nissan/altima;color=grey/2005
    * URL - http://localhost:8080/RESTFulWS/rest/CarResourceMatrixAndPathParam/segment/honda/accord;color=grey/2012
    * URL - http://localhost:8080/RESTFulWS/rest/CarResourceMatrixAndPathParam/segment/infinity/qx60;color=black/2017
    * 
    * @param make
    * @param car
    * @param year
    * @return
    */
   @GET
   @Path("/segment/{make}/{model}/{year}")
   @Produces("text/plain")
   public String getFromPathSegment(@PathParam("make") String make,
                                    @PathParam("model") PathSegment car,
                                    @PathParam("year") String year)
   {
      String carColor = car.getMatrixParameters().getFirst("color");
      return "A " + carColor + " " + year + " " + make + " " + car.getPath();
   }
   
   

   /**
    * URL - http://localhost:8080/RESTFulWS/rest/CarResourceMatrixAndPathParam/segments/nissan/SE/2D/altima/year/2005
    * URL - http://localhost:8080/RESTFulWS/rest/CarResourceMatrixAndPathParam/segments/honda/VE/6C/accord/year/2012
    * URL - http://localhost:8080/RESTFulWS/rest/CarResourceMatrixAndPathParam/segments/infinity/LE/qx60/year/2017
    * 
    * @param make
    * @param car
    * @param year
    * @return
    */
   @GET
   @Path("/segments/{make}/{model : .+}/year/{year}")
   @Produces("text/plain")
   public String getFromMultipleSegments(@PathParam("make") String make,
                                         @PathParam("model") List<PathSegment> car,
                                         @PathParam("year") String year)
   {
      String output = "A " + year + " " + make;
      for (PathSegment segment : car)
      {
         output += " " + segment.getPath();
      }
      return output;
   }

   /**
    * URL - http://localhost:8080/RESTFulWS/rest/CarResourceMatrixAndPathParam/uriinfo/nissan/altima;color=grey/2005
    * URL - http://localhost:8080/RESTFulWS/rest/CarResourceMatrixAndPathParam/uriinfo/honda/accord;color=grey/2012
    * URL - http://localhost:8080/RESTFulWS/rest/CarResourceMatrixAndPathParam/uriinfo/infinity/qx60;color=black/2017
    * 
    * @param info
    * @return
    */
   @GET
   @Path("/uriinfo/{make}/{model}/{year}")
   @Produces("text/plain")
   public String getFromUriInfo(@Context UriInfo info)
   {
      String make = info.getPathParameters().getFirst("make");
      String year = info.getPathParameters().getFirst("year");
      PathSegment model = info.getPathSegments().get(3);
      String color = model.getMatrixParameters().getFirst("color");

      return "A " + color + " " + year + " " + make + " " + model.getPath();
   }
}
