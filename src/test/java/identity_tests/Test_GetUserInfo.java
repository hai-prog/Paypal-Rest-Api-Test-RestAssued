package identity_tests;

import Identity_api.UserInfo;
import http_methods.GetRequest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import paypal_server.PaypalServer;
import validations.CommonValidations;

public class Test_GetUserInfo {


    SoftAssert softAssert;

    UserInfo userInfo;

    CommonValidations commonValidations;

    @BeforeTest
    public void sendRequest() {
        softAssert = new SoftAssert();
        userInfo = new UserInfo();
        commonValidations = new CommonValidations();


    }


    @Test(description = "A successful request returns the HTTP 200 OK status")
    public void validateSuccessfulRequest() {

        var response = userInfo.getUserInfo("paypalv1.1");

        // assertions
        commonValidations.validateTheStatusCode(response, 200);
    }


    @Test(description = "Invalid request. The schema parameter is not valid.")
    public void validateErrorHandlingWithIncorrectParam() {
        String schema = "paypalv1.";
        var response = userInfo.getUserInfo(schema);

        // assertions
        commonValidations.validateTheStatusCode(response, 400);
        commonValidations.validateAParameterOfTheResponse(response, "error", "invalid_request");
        commonValidations.validateAParameterOfTheResponse(response, "error_description", "Invalid schema request " + schema);
    }

    @Test(description = "Invalid access token. The bearer token contains an incorrect access token")
    public void validateErrorHandlingWithUnAuthorizedAccess() {
        GetRequest getRequest = new GetRequest(PaypalServer.uri, PaypalServer.identityEndpoint);
        getRequest.addHeader("Authorization", "Bearer 21AAIteXteurXcHR_ruVifi1t08Sx39tSb6Is_DR_eeJASZelEt2s-I19d7rUbVK9aHpuzWSVz94mZZXeTbUjLtAMln4g4mQ");
        getRequest.addQueryParameter("schema", "paypalv1.1");
        Response response = getRequest.send();

        // assertions
        commonValidations.validateTheStatusCode(response, 401);
        commonValidations.validateAParameterOfTheResponse(response, "error", "invalid_token");
        commonValidations.validateAParameterOfTheResponse(response, "error_description", "Token signature verification failed");

    }


}


//    @Test(description = "Invalid request. The schema parameter is not valid.")
//    public void validateErrorHandlingWithIncorrectParam1() {
//        String schema = "paypalv1.";
//        getRequest.addHeader("Authorization", "Bearer " + accessToken);
//        getRequest.addQueryParameter("schema", schema);
//        Response response = getRequest.send();
//
//        // assertions
//        softAssert.assertEquals(response.statusCode(), 400);
//        softAssert.assertEquals(response.jsonPath().getString("error"), "invalid_request");
//        softAssert.assertEquals(response.jsonPath().getString("error_description"), "Invalid schema request " + schema);
//        softAssert.assertAll();
//    }