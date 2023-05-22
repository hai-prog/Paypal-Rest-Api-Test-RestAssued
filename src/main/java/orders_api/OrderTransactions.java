package orders_api;

import authorization.AccessTokenGeneration;
import http_methods.GetRequest;
import http_methods.PostRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import paypal_server.PaypalServer;

public class OrderTransactions {

    PostRequest postRequest;
    GetRequest getRequest;
    String accessToken = AccessTokenGeneration.getAccessToken();

    /**
     * This method is used to create order
     *
     * @param body the body of your request
     * @return the response of your request
     */
    @Step("Create an order")
    public Response createOrder(String body) {
        postRequest = new PostRequest(PaypalServer.uri, PaypalServer.ordersEndPoint);
        postRequest.addHeader("Authorization", "Bearer " + accessToken);
        postRequest.addHeader("Prefer", "return=representation");
        postRequest.addHeader("PayPal-Request-Id", "A v4 style guid");
        postRequest.addBody(body);
        return postRequest.send();
    }


    /**
     * this method to get the order that you created
     *
     * @param orderID the ID Of the order you created
     * @return the response with data about the order you created
     */
    @Step("Get an order")
    public Response getOrder(String orderID) {
        getRequest = new GetRequest(PaypalServer.uri, PaypalServer.ordersEndPoint + "/" + orderID);
        getRequest.addHeader("Authorization", "Bearer " + accessToken);
        return getRequest.send();
    }


    //    public static Response createRequest1(String body ){
//       RequestSpecification requestSpecification = RestAssured.given().baseUri(PaypalServer.uri).contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + accessToken)
//                .header("Prefer", "return=representation")
//                .header("PayPal-Request-Id", "A v4 style guid");
//
//
//        Response response = given()
//                .spec(requestSpecification)
//                .body(body)
//                .when()
//                .post(PaypalServer.ordersEndPoint);
//        return response;
//
//    }


}
