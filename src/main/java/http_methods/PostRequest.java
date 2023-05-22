package http_methods;

import api_request.APIRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class PostRequest extends APIRequest {
    public PostRequest(String uri, String endpoint) {
        super(uri, endpoint);
    }

    public void addBody(String body) {
        requestSpecification.body(body);
    }

    @Step("Post request")
    public Response send() {
        return requestSpecification.post(endpoint);
    }
}
