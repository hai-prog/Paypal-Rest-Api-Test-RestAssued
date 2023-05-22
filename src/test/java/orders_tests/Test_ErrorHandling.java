package orders_tests;

import orders_api.OrderTransactions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import validations.CommonValidations;

public class Test_ErrorHandling {


    SoftAssert softAssert;

    CommonValidations commonValidations;
    OrderTransactions orderTransactions;


    @BeforeTest
    public void sendRequest() {
        softAssert = new SoftAssert();
        commonValidations = new CommonValidations();
        orderTransactions = new OrderTransactions();
    }

    @Test(description = "Order Not Found request : returns the HTTP 404 Not Found status")
    public void validateErrorHandlingWithOrderNotFound() {

        var response = orderTransactions.getOrder("51R18997VL852680D");

        // assertions
        commonValidations.validateTheStatusCode(response, 404);
        commonValidations.validateErrorHandling(response, "RESOURCE_NOT_FOUND", "INVALID_RESOURCE_ID", "Specified resource ID does not exist. Please check the resource ID and try again.");


    }

    @Test(description = "  Invalid Currency Code returns the HTTP 422 Unprocessable Entity status")
    public void validateErrorHandlingWithInvalidCurrencyCode() {

        String requestBody = """
                {
                        "intent": "CAPTURE",
                        "purchase_units": [
                            {
                                "amount": {
                                    "currency_code": "ABC",
                                    "value": "100.00"
                                }
                            }
                        ]
                    }
                """;
        var response = orderTransactions.createOrder(requestBody);


        // assertions
        commonValidations.validateTheStatusCode(response, 422);
        commonValidations.validateErrorHandling(response, "UNPROCESSABLE_ENTITY", "INVALID_CURRENCY_CODE", "Currency code is invalid.");

    }

    @Test(description = " Invalid Parameter Value returns the HTTP 400 Bad Request status ")
    public void validateErrorHandlingWithInvalidParameter() {

        String requestBody = """
                {
                     "intent": "SHIPPING",
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

        // assertions
        commonValidations.validateTheStatusCode(response, 400);
        commonValidations.validateAParameterOfTheResponse(response, "name", "INVALID_REQUEST");
        commonValidations.validateAParameterOfTheResponse(response, "message", "Request is not well-formed");
        commonValidations.validateAParameterOfTheResponse(response, "details.issue", "INVALID_PARAMETER_VALUE");

    }

    @Test(description = " Missing Required Parameter returns the HTTP 400 Bad Request status")
    public void validateErrorHandlingWithMissingRequiredParameter() {

        String requestBody = """
                {
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

        // assertions
        commonValidations.validateTheStatusCode(response, 400);
        commonValidations.validateAParameterOfTheResponse(response, "name", "INVALID_REQUEST");
        commonValidations.validateAParameterOfTheResponse(response, "message", "Request is not well-formed");
        commonValidations.validateAParameterOfTheResponse(response, "details.issue", "MISSING_REQUIRED_PARAMETER");

    }

    @Test(description = " Amount Value Exceeded(Should be less than or equal to 9999999.99) returns the HTTP 422 Unprocessable Entity status")
    public void validateErrorHandlingWithAmountValueExceeded() {

        String requestBody = """
                {
                        "intent": "CAPTURE",
                        "purchase_units": [
                            {
                                "amount": {
                                    "currency_code": "USD",
                                    "value": "1000000000.00"
                                }
                            }
                        ]
                    }
                """;
        var response = orderTransactions.createOrder(requestBody);


        // assertions
        commonValidations.validateTheStatusCode(response, 422);
        commonValidations.validateErrorHandling(response, "UNPROCESSABLE_ENTITY", "[MAX_VALUE_EXCEEDED]", "[Should be less than or equal to 9999999.99.]");

    }


    @Test(description = " Item Total Mismatch(Should equal sum of (unit_amount * quantity) across all items for a given purchase_unit) returns the HTTP 422 Unprocessable Entity status")
    public void validateErrorHandlingWithItemTotalMismatch() {

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
                                      },
                                      {
                                          "name": "Jeans",
                                          "description": "Blue M",
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


        // assertions
        commonValidations.validateTheStatusCode(response, 422);
        commonValidations.validateErrorHandling(response, "UNPROCESSABLE_ENTITY", "ITEM_TOTAL_MISMATCH", "Should equal sum of (unit_amount * quantity) across all items for a given purchase_unit");

    }

    @Test(description = " Payee Not Enabled For Card Processing(Payee account is not setup to be able to process card payments) returns the HTTP 422 Unprocessable Entity status")
    public void validateErrorHandlingWithPayeeNotEnabled() {

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
                           ],
                           "payment_source": {
                               "card": {
                                   "number": "4242424242424242",
                                   "expiry": "2024-02"
                               }
                           }
                       }
                """;
        var response = orderTransactions.createOrder(requestBody);


        // assertions
        commonValidations.validateTheStatusCode(response, 422);
        commonValidations.validateErrorHandling(response, "UNPROCESSABLE_ENTITY", "NOT_ENABLED_FOR_CARD_PROCESSING", "The API Caller account is not setup to be able to process card payments");

    }


}
