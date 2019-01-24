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
import io.swagger.model.Primitive;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * CaseOfUse
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2019-01-24T12:57:09.429Z[GMT]")public class CaseOfUse   {
  @JsonProperty("cuName")
  private String cuName = null;

  @JsonProperty("usedVar")
  private List<String> usedVar = null;

  @JsonProperty("lPrimitives")
  private List<Primitive> lPrimitives = null;

  public CaseOfUse cuName(String cuName) {
    this.cuName = cuName;
    return this;
  }

  /**
   * Get cuName
   * @return cuName
   **/
  @JsonProperty("cuName")
  @Schema(description = "")
  public String getCuName() {
    return cuName;
  }

  public void setCuName(String cuName) {
    this.cuName = cuName;
  }

  public CaseOfUse usedVar(List<String> usedVar) {
    this.usedVar = usedVar;
    return this;
  }

  public CaseOfUse addUsedVarItem(String usedVarItem) {
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

  public CaseOfUse lPrimitives(List<Primitive> lPrimitives) {
    this.lPrimitives = lPrimitives;
    return this;
  }

  public CaseOfUse addLPrimitivesItem(Primitive lPrimitivesItem) {
    if (this.lPrimitives == null) {
      this.lPrimitives = new ArrayList<Primitive>();
    }
    this.lPrimitives.add(lPrimitivesItem);
    return this;
  }

  /**
   * Get lPrimitives
   * @return lPrimitives
   **/
  @JsonProperty("lPrimitives")
  @Schema(description = "")
  public List<Primitive> getLPrimitives() {
    return lPrimitives;
  }

  public void setLPrimitives(List<Primitive> lPrimitives) {
    this.lPrimitives = lPrimitives;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CaseOfUse caseOfUse = (CaseOfUse) o;
    return Objects.equals(this.cuName, caseOfUse.cuName) &&
        Objects.equals(this.usedVar, caseOfUse.usedVar) &&
        Objects.equals(this.lPrimitives, caseOfUse.lPrimitives);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cuName, usedVar, lPrimitives);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CaseOfUse {\n");
    
    sb.append("    cuName: ").append(toIndentedString(cuName)).append("\n");
    sb.append("    usedVar: ").append(toIndentedString(usedVar)).append("\n");
    sb.append("    lPrimitives: ").append(toIndentedString(lPrimitives)).append("\n");
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
