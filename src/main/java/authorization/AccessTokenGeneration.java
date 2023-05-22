package authorization;

import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class AccessTokenGeneration {

    @Step("Generate access token")
    public static String getAccessToken() {

        var response = given()
                .header("Authorization", "Basic QVV2OHJyY19QLUViUDJFMG1wYjQ5QlY3ckZ0M1Vzci12ZFVaTzhWR09ualJlaEdIQlhrU3pjaHIzN1NZRjJHTmRRRllTcDcyamg1UVVoekc6RU1uQVdlMDZpb0d0b3VKczdnTFlUOWNoSzktMmpKLS03TUtSWHBJOEZlc21ZXzJLcC1kXzdhQ3FmZjdNOW1vRUpCdnVYb0JPNGNsS3RZMHY=")
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .formParam("return_authn_schemes", true)
                .formParam("ignoreCache", true)
                .formParam("return_client_metadata", true)
                .formParam("return_unconsented_scopes", true)

                .when().post("https://api-m.sandbox.paypal.com/v1/oauth2/token").then();


        String accessToken = response.extract().path("access_token");


        return accessToken;

    }
}
