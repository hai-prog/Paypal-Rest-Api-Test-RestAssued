package http_methods;

import api_request.APIRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class PatchRequest extends APIRequest {
    public PatchRequest(String uri, String endpoint) {
        super(uri, endpoint);
    }

    @Step("Patch request")
    @Override
    public Response send() {
        return requestSpecification.patch(endpoint);
    }


}
