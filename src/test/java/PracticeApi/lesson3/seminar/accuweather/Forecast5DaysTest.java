package PracticeApi.lesson3.seminar.accuweather;

import PracticeApi.lesson3.home.accuweather.weather.DailyForecast;
import PracticeApi.lesson3.home.accuweather.weather.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Forecast5DaysTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Get response")
    void testGetResponse() {
        //авторизовались и опеределили все в переменную response
        String response = given().queryParam("apikey", getApiKey()).pathParam("localKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/5day/{localKey}")// выполнили операцию с запросом
                .then().statusCode(200).time(lessThan(5000l)) // проверили на успешность операции не больше 1 сек
                .extract().asString();// возращает ответ в виде строки
        System.out.println(response);
    }

    @Test
    @Disabled
    @DisplayName("Get body response")
    void testGetBodyResponse() {
        //авторизовались и опеределили все в переменную response
        Weather response = given().queryParam("apikey", getApiKey()).pathParam("localKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/5day/{localKey}")// выполнили операцию с запросом
                .then().statusCode(200).time(lessThan(5000l)) // проверили на успешность операции не больше 1 сек
                .extract().response().body().as(Weather.class);// возращает ответ в виде обьекта Weather;
        // извлечение тела ответа по классу Weather;
        Assertions.assertEquals(5, response.getDailyForecasts().size());// size getDailyForecasts() имеет 5 обьектов
    }

    @Test
    @Disabled
    void testGetParamsResponse() {
        Weather response = given().queryParam("apikey", getApiKey()).pathParam("localKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/5day/{localKey}")
                .then().statusCode(200).time(lessThan(5000L))
                .extract().response().body().as(Weather.class);
        Assertions.assertEquals("F", response.getDailyForecasts().get(0).getTemperature().getMaximum().getUnit());
    }

    @Test
    @Disabled
    @DisplayName("Test Contains Elements in List Response ")
    void testGetParamsResponseAsList() {
        List<DailyForecast> response = given().queryParam("apikey", getApiKey()).pathParam("localKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/5day/{localKey}")
                .then().statusCode(200).time(lessThan(5000L))
                .extract().body().jsonPath().getList("DailyForecasts",DailyForecast.class);
        Assertions.assertEquals(5,response.size());
        Assertions.assertEquals("F",response.get(0).getTemperature().getMinimum().getUnit());
        Assertions.assertEquals("F",response.get(0).getTemperature().getMaximum().getUnit());

    }
}