package PracticeApi.lesson3.home.ForecastAndLocationPracticeApi;

import PracticeApi.lesson3.home.accuweather.weather.DailyForecast;
import PracticeApi.lesson3.seminar.accuweather.weather.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class OneDayOfDailyForecastsTest extends AbstractAccuweatherTest {
    @Test
    @DisplayName("Get status code 200 in Accuweather response & number of char symbols in response")
    void testGetResponseAndNumberSymbols() {
        String response = given().queryParam("apikey", getApiKey()).pathParam("localKey", 1)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/1day/{localKey}")
                .then().statusCode(200).time(lessThan(5000l))
                .extract().asString();
        Assertions.assertEquals(1067, response.length());
        System.out.println("Number of char symbols in response:" + response.length());
        System.out.println(response);
    }

    @Test
    @DisplayName("Test Contains Element 'Day' in String Response ")
    void testGetElementResponseAsString() {
        String response = given().queryParam("apikey", getApiKey()).pathParam("localKey", 1)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/1day/{localKey}")
                .then().statusCode(200).time(lessThan(5000L))
                .extract().asString();
        Assertions.assertTrue(response.contains("Day"));
    }

    @Test
    @DisplayName("Get size from element in Object Response")
    void testGetBodyResponseElements() {
        Weather response = given().queryParam("apikey", getApiKey()).pathParam("localKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/1day/{localKey}")
                .then().statusCode(200).time(lessThan(5000l))
                .extract().response().body().as(Weather.class);
        Assertions.assertEquals(1, response.getDailyForecasts().size());
    }

    @Test
    @DisplayName("Test Contains Elements in List Response ")
    void testGetParamsResponseAsList() {
        List<DailyForecast> response = given().queryParam("apikey", getApiKey()).pathParam("localKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/1day/{localKey}")
                .then().statusCode(200).time(lessThan(5000L))
                .extract().body().jsonPath().getList("DailyForecasts", DailyForecast.class);
        Assertions.assertEquals(1, response.size());
        //   Assertions.assertEquals(4, response.get(0).getDay().getIcon());
        //   Assertions.assertEquals(false, response.get(0).getNight().getHasPrecipitation());
    }
}
