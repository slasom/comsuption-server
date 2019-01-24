package io.swagger.api;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
    info = @Info(
        title = "Swagger Server", 
        version = "1.0", 
        description = "API Consumptions, calculates the battery consumption and data traffic of your application, simulating the development with different architectures, in order to choose the most optimal.  Following the scheme that is specified to build the JSON, the API returns the results with respect to the different architectures and also about their use cases.  Schemas      APP.     -name: Name of APP.     -architecturesView: Option to show or not the results of an architecture.     -lVariables: List of variables of your application.     -lArchitectures: List of arquitectures of your application.          Variable.     -varName: Name of the variable.     -values: List of values of the variable (float or int).          Architecture.     -arName: Name of architecture.     -caseOfUses: List of case of uses of your application.          CaseOfUse.     -cuName: Name of case of use.     -usedVar: Variables (only varName) that are used in the CU.     -lPrimitives: List of primitives of the use case.          CaseOfUse.     -type: Type of the primitive (store, post, get, getGPS and receivePush).     -usedVar: Variables (only varName) that are used in the primitive.     -lOperations: List of operations of the primitive.          Operation.     -size: Size of the operation (in Bytes).     -usedVar: Variables (only varName) that are used in the operation.",
        termsOfService = "",
        contact = @Contact(email = ""),
        license = @License(
            name = "",
            url = "http://unlicense.org"
        )
    )
)
public class Bootstrap {
}
