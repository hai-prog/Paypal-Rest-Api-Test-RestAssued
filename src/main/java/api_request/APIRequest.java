package api_request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class APIRequest {
    public RequestSpecification requestSpecification;
    public String uri;
    public String endpoint;

    public APIRequest(String uri, String endpoint) {
        this.uri = uri;
        this.endpoint = endpoint;
        requestSpecification = RestAssured.given().baseUri(uri).contentType(ContentType.JSON);
    }

    public abstract Response send();

    public void addHeader(String key, String value) {
        requestSpecification.header(key, value);
    }

    public void addQueryParameter(String param, String value) {
        requestSpecification.queryParam(param,value);
    }

    public void addPathParameter(String param, String value) {
        requestSpecification.pathParam(param,value);

    }

    public void addBody(String body) {
        requestSpecification.body(body);
    }

}
