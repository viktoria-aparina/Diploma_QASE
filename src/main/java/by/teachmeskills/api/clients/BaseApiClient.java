package by.teachmeskills.api.clients;

import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseApiClient {

    RequestSpecification rqSpec;

    public BaseApiClient() {
        rqSpec = given()
                .config(RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Token", System.getenv("Token"))
                .baseUri("https://api.qase.io/")
                .log().ifValidationFails();
    }

    public Response post(String uri, Object body, Map<String, ?> parameterNameValuePairs) {
        return given().spec(rqSpec)
                      .pathParams(parameterNameValuePairs)
                      .body(body)
                      .log().ifValidationFails()
                      .when()
                      .post(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response basePostProject(String uri, Object body) {
        return given().spec(rqSpec)
                      .body(body)
                      .log().ifValidationFails()
                      .when()
                      .post(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response basePostAttachment(String uri, File attachment, Map<String, ?> parameterNameValuePairs) {
        return given()
                .contentType(ContentType.MULTIPART)
                .accept(ContentType.JSON)
                .header("Token", System.getenv("Token"))
                .baseUri("https://api.qase.io/")
                .multiPart("file", attachment, "image/x-png")
                .log().ifValidationFails()
                .pathParams(parameterNameValuePairs)
                .log().ifValidationFails()
                .when()
                .post(uri)
                .then()
                .log().ifValidationFails()
                .extract()
                .response();
    }

    public Response basePostMilestone(String uri, Object body, Map<String, ?> parameterNameValuePairs) {
        return given().spec(rqSpec)
                      .pathParams(parameterNameValuePairs)
                      .body(body)
                      .log().ifValidationFails()
                      .when()
                      .post(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response get(String uri, Map<String, ?> parameterNameValuePairs) {
        return given().spec(rqSpec)
                      .pathParams(parameterNameValuePairs)
                      .when()
                      .get(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response getAll(String uri, Map<String, ?> parameterNameValuePairsPath, Map<String, ?> parameterNameValuePairsQuery) {
        return given().spec(rqSpec)
                      .pathParams(parameterNameValuePairsPath)
                      .queryParams(parameterNameValuePairsQuery)
                      .when()
                      .get(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response delete(String uri, Map<String, ?> parameterNameValuePairs) {
        return given().spec(rqSpec)
                      .pathParams(parameterNameValuePairs)
                      .when()
                      .delete(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response baseDeleteProject(String uri, Map<String, ?> parameterNameValuePairs) {
        return given().spec(rqSpec)
                      .pathParams(parameterNameValuePairs)
                      .when()
                      .delete(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response patchBodyDefect(String uri, Object body, Map<String, ?> parameterNameValuePairs) {
        return given().spec(rqSpec)
                      .pathParams(parameterNameValuePairs)
                      .body(body)
                      .log().ifValidationFails()
                      .when()
                      .patch(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }

    public Response patchResolve(String uri, Map<String, ?> parameterNameValuePairs) {
        return given().spec(rqSpec)
                      .pathParams(parameterNameValuePairs)
                      .log().ifValidationFails()
                      .when()
                      .patch(uri)
                      .then()
                      .log().ifValidationFails()
                      .extract()
                      .response();
    }
}
