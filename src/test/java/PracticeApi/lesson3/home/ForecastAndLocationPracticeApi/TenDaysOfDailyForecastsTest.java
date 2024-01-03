package PracticeApi.lesson3.home.ForecastAndLocationPracticeApi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TenDaysOfDailyForecastsTest extends AbstractAccuweatherTest {

    @Test
    @DisplayName("Get status code 401 in Accuweather response for Ten Days of Daily Forecasts")
    void testGetResponseTenForecastDays() {
        String response = given().queryParam("apikey", getApiKey()).pathParam("localKey", 1)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/10day/{localKey}")
                .then().statusCode(401).time(lessThan(5000l))
                .extract().asString();
        System.out.println(response);
    }
}
