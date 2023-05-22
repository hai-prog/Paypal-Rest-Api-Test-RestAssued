package orders_tests;

import orders_api.OrderTransactions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import validations.CommonValidations;

public class Test_CRUDOrderSuccessfully {

    SoftAssert softAssert;
    CommonValidations commonValidations;

    OrderTransactions orderTransactions;
    String orderID;

    @BeforeTest
    public void sendRequest() {
        softAssert = new SoftAssert();
        commonValidations = new CommonValidations();
        orderTransactions = new OrderTransactions();


    }


    @Test(description = "A successful request : create order with detailed request returns the HTTP 201 Created status")
    public void validateSuccessfulPostRequestWithDetailedRequest() {


        String requestBody = """
                {
                    "intent": "CAPTURE",
                    "purchase_units": [
                        {
                            "items": [
                                {
                                    "name": "T-Shirt",
                                    "description": "Green XL",
                                    "quantity": "1",
                                    "unit_amount": {
                                        "currency_code": "USD",
                                        "value": "100.00"
                                    }
                                }
                            ],
                            "amount": {
                                "currency_code": "USD",
                                "value": "100.00",
                                "breakdown": {
                                    "item_total": {
                                        "currency_code": "USD",
                                        "value": "100.00"
                                    }
                                }
                            }
                        }
                    ],
                    "application_context": {
                        "return_url": "https://example.com/return",
                        "cancel_url": "https://example.com/cancel"
                    }
                }
                """;
        var response = orderTransactions.createOrder(requestBody);

        // get the order id
        orderID = response.jsonPath().getString("id");

        // assertions
        commonValidations.validateTheStatusCode(response, 200);
        commonValidations.validateAParameterOfTheResponse(response, "status", "CREATED");
    }


    @Test(description = "A successful request : create order with minimal request returns the HTTP 201 Created status")
    public void validateSuccessfulPostRequestWithMinimalRequest() {

        //postRequest = new PostRequest(PaypalServer.uri, PaypalServer.ordersEndPoint);

        String requestBody = """
                {
                     "intent": "CAPTURE",
                     "purchase_units": [
                         {
                             "amount": {
                                 "currency_code": "USD",
                                 "value": "100.00"
                             }
                         }
                     ]
                 }
                """;

        var response = orderTransactions.createOrder(requestBody);

        // get the order id
        orderID = response.jsonPath().getString("id");

        // assertions

        commonValidations.validateTheStatusCode(response, 200);
        commonValidations.validateAParameterOfTheResponse(response, "status", "CREATED");
    }


    @Test(description = "A successful get request to check if the order is created : returns the HTTP 200 Created status", dependsOnMethods = "validateSuccessfulPostRequestWithDetailedRequest")
    public void validateSuccessfulGetRequest() {

        var response = orderTransactions.getOrder(orderID);

        // assertions
        commonValidations.validateTheStatusCode(response, 200);
        commonValidations.validateAParameterOfTheResponse(response, "status", "CREATED");
    }


}

