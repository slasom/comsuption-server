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
import io.swagger.model.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * Primitive
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2019-01-24T12:57:09.429Z[GMT]")public class Primitive   {
  @JsonProperty("type")
  private String type = null;

  @JsonProperty("usedVar")
  private List<String> usedVar = null;

  @JsonProperty("lOperations")
  private List<Operation> lOperations = null;

  public Primitive type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   **/
  @JsonProperty("type")
  @Schema(description = "")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Primitive usedVar(List<String> usedVar) {
    this.usedVar = usedVar;
    return this;
  }

  public Primitive addUsedVarItem(String usedVarItem) {
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

  public Primitive lOperations(List<Operation> lOperations) {
    this.lOperations = lOperations;
    return this;
  }

  public Primitive addLOperationsItem(Operation lOperationsItem) {
    if (this.lOperations == null) {
      this.lOperations = new ArrayList<Operation>();
    }
    this.lOperations.add(lOperationsItem);
    return this;
  }

  /**
   * Get lOperations
   * @return lOperations
   **/
  @JsonProperty("lOperations")
  @Schema(description = "")
  public List<Operation> getLOperations() {
    return lOperations;
  }

  public void setLOperations(List<Operation> lOperations) {
    this.lOperations = lOperations;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Primitive primitive = (Primitive) o;
    return Objects.equals(this.type, primitive.type) &&
        Objects.equals(this.usedVar, primitive.usedVar) &&
        Objects.equals(this.lOperations, primitive.lOperations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, usedVar, lOperations);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Primitive {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    usedVar: ").append(toIndentedString(usedVar)).append("\n");
    sb.append("    lOperations: ").append(toIndentedString(lOperations)).append("\n");
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
