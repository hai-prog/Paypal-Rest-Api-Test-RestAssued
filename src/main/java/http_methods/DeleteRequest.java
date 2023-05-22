package http_methods;

import api_request.APIRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class DeleteRequest extends APIRequest {
    public DeleteRequest(String uri, String endpoint) {
        super(uri, endpoint);
    }

    @Step("Delete request")
    public Response send() {
        return requestSpecification.delete(endpoint);
    }
}
