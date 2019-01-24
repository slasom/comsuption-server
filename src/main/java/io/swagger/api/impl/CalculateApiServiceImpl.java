package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.api.services.AplicacionServices;
import io.swagger.model.*;

import io.swagger.model.App;

import java.text.ParseException;
import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2019-01-24T12:57:09.429Z[GMT]")public class CalculateApiServiceImpl extends CalculateApiService {

    private AplicacionServices appServices= new AplicacionServices();
    @Override
    public Response calculatePost(String app, SecurityContext securityContext) throws NotFoundException {
        try {
            return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, appServices.calculate(app))).build();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
            return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Error en el Parseo del JSON")).build();
        }

    }
}
