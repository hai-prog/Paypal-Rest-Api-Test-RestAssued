package Identity_api;

import authorization.AccessTokenGeneration;
import http_methods.GetRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import paypal_server.PaypalServer;

public class UserInfo {

    GetRequest getRequest;
    static String accessToken = AccessTokenGeneration.getAccessToken();

    /**
     * this method used to get the user profile info
     * @param paramValue it is the query parameter value
     * @return the response with the user info
     */
    @Step("Get user info")
    public Response getUserInfo(String paramValue)
    {
        getRequest = new GetRequest(PaypalServer.uri,PaypalServer.identityEndpoint);
        getRequest.addHeader("Authorization", "Bearer " + accessToken);
        getRequest.addQueryParameter("schema", paramValue);
        return getRequest.send();
    }
}
