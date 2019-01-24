package io.swagger.api.factories;

import io.swagger.api.CalculateApiService;
import io.swagger.api.impl.CalculateApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2019-01-24T12:57:09.429Z[GMT]")public class CalculateApiServiceFactory {
    private final static CalculateApiService service = new CalculateApiServiceImpl();

    public static CalculateApiService getCalculateApi() {
        return service;
    }
}
