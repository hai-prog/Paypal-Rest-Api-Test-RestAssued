package validations;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class CommonValidations {
    /**
     * this method is used to validate the status code of the response
     *
     * @param response   the response of your request
     * @param statusCode
     */
    @Step("validateTheStatusCode")
    public void validateTheStatusCode(Response response, int statusCode) {
        Assert.assertEquals(response.statusCode(), statusCode);

    }

    /**
     * this method to check the status of your successful request if created or not
     *
     * @param response       the response of your request
     * @param jsonPath       the jsonPath of the parameter that you want to parse
     * @param expectedValue  the expected value for the parameter that you want to validate it
     */
    @Step("validateTheStatusOfYorRequest")
    public void validateAParameterOfTheResponse(Response response, String jsonPath , String expectedValue) {
        Assert.assertTrue(response.jsonPath().getString(jsonPath).contains(expectedValue));
    }

    /**
     * this method to check the( Name , reason and the description ) of your request is unsuccessful
     *
     * @param response         the response of your request
     * @param errorName        you get it from the response body
     * @param errorReason      you get it from the response body
     * @param errorDescription you get it from the response body
     */
    @Step("validateTheErrorMessage")
    public void validateErrorHandling(Response response, String errorName, String errorReason, String errorDescription) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.jsonPath().getString("name"), errorName);
        softAssert.assertTrue(response.jsonPath().getString("details.issue").contains(errorReason));
        softAssert.assertTrue(response.jsonPath().getString("details.description").contains(errorDescription));
        softAssert.assertAll();
    }


}
