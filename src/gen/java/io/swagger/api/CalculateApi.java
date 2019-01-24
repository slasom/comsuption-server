package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.CalculateApiService;
import io.swagger.api.factories.CalculateApiServiceFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import io.swagger.model.App;

import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/calculate")


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2019-01-24T12:57:09.429Z[GMT]")public class CalculateApi  {
   private final CalculateApiService delegate;

   public CalculateApi(@Context ServletConfig servletContext) {
      CalculateApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("CalculateApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (CalculateApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = CalculateApiServiceFactory.getCalculateApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "text/plain", "application/json" })
    @Produces({ "text/plain" })
    @Operation(summary = "Calculation of battery consumption and data traffic.", description = "Returns the results of battery consumption and data traffic related to your application.", tags={ "Consumptions" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = String.class))),
        
        @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = String.class))),
        
        @ApiResponse(responseCode = "404", description = "Not found response", content = @Content(schema = @Schema(implementation = String.class))),
        
        @ApiResponse(responseCode = "405", description = "Invalid input") })
    public Response calculatePost(@Parameter(description = "" ,required=true) String body

,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.calculatePost(body,securityContext);
    }
}
