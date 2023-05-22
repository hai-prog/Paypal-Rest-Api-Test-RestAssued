package http_methods;

import api_request.APIRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class GetRequest extends APIRequest {

    public GetRequest(String uri, String endpoint) {
        super(uri, endpoint);
    }

    @Step("Get request")
    @Override
    public Response send() {
        return requestSpecification.get(endpoint);
    }

}
