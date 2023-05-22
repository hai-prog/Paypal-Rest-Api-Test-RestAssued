package http_methods;

import api_request.APIRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class PutRequest extends APIRequest {

    public PutRequest(String uri, String endpoint) {
        super(uri, endpoint);
    }

    public void addBody(String body) {
        requestSpecification.body(body);
    }

    @Step("Put request")
    public Response send() {
        return requestSpecification.put(endpoint);
    }
}
