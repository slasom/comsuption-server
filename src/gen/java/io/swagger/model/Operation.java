/*
 * API Consumptions
 * API Consumptions, calculates the battery consumption and data traffic of your application, simulating the development with different architectures, in order to choose the most optimal.  Following the scheme that is specified to build the JSON, the API returns the results with respect to the different architectures and also about their use cases.  Schemas      APP.     -name: Name of APP.     -architecturesView: Option to show or not the results of an architecture.     -lVariables: List of variables of your application.     -lArchitectures: List of arquitectures of your application.          Variable.     -varName: Name of the variable.     -values: List of values of the variable (float or int).          Architecture.     -arName: Name of architecture.     -caseOfUses: List of case of uses of your application.          CaseOfUse.     -cuName: Name of case of use.     -usedVar: Variables (only varName) that are used in the CU.     -lPrimitives: List of primitives of the use case.          CaseOfUse.     -type: Type of the primitive (store, post, get, getGPS and receivePush).     -usedVar: Variables (only varName) that are used in the primitive.     -lOperations: List of operations of the primitive.          Operation.     -size: Size of the operation (in Bytes).     -usedVar: Variables (only varName) that are used in the operation.
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * Operation
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2019-01-24T12:57:09.429Z[GMT]")public class Operation   {
  @JsonProperty("size")
  private Float size = null;

  @JsonProperty("usedVar")
  private List<String> usedVar = null;

  public Operation size(Float size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * @return size
   **/
  @JsonProperty("size")
  @Schema(description = "")
  public Float getSize() {
    return size;
  }

  public void setSize(Float size) {
    this.size = size;
  }

  public Operation usedVar(List<String> usedVar) {
    this.usedVar = usedVar;
    return this;
  }

  public Operation addUsedVarItem(String usedVarItem) {
    if (this.usedVar == null) {
      this.usedVar = new ArrayList<String>();
    }
    this.usedVar.add(usedVarItem);
    return this;
  }

  /**
   * Get usedVar
   * @return usedVar
   **/
  @JsonProperty("usedVar")
  @Schema(description = "")
  public List<String> getUsedVar() {
    return usedVar;
  }

  public void setUsedVar(List<String> usedVar) {
    this.usedVar = usedVar;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Operation operation = (Operation) o;
    return Objects.equals(this.size, operation.size) &&
        Objects.equals(this.usedVar, operation.usedVar);
  }

  @Override
  public int hashCode() {
    return Objects.hash(size, usedVar);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Operation {\n");
    
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    usedVar: ").append(toIndentedString(usedVar)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}