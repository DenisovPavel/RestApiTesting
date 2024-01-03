package PracticeApi.lesson3.home.ForecastAndLocationPracticeApi;

import PracticeApi.lesson3.seminar.accuweather.location.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class LocationsAccuweatherTest extends AbstractAccuweatherTest {
    @Test
    @DisplayName("Test Autocomplete City-Name")
    void testLocationsAsStringCheckResponse() {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", getApiKey());
        params.put("q", "De");
        String response = given().queryParams(params)
                .when().get(getBaseUrl() + "/locations/v1/cities/autocomplete")
                .then().statusCode(200).time(lessThan(1000L))
                .extract().asString();
        System.out.println(response);
    }

    @Test
    @DisplayName("Test Autocomplete check size")
    void testLocationsAsLocationCheckSize() {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", getApiKey());
        params.put("q", "De");
        List<Location> response = given().queryParams(params)
                .when().get(getBaseUrl() + "/locations/v1/cities/autocomplete")
                .then().statusCode(200).time(lessThan(1000L))
                .extract().body().jsonPath().getList(".", Location.class);
        Assertions.assertEquals(10, response.size());


    }

    @Test
    @DisplayName("Test Autocomplete check Country: India and LocalizedName: Delhi ")
    void testLocationsAsLocationCheckLocalizedName() {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", getApiKey());
        params.put("q", "De");
        List<Location> response = given().queryParams(params)
                .when().get(getBaseUrl() + "/locations/v1/cities/autocomplete")
                .then().statusCode(200).time(lessThan(1000L))
                .extract().body().jsonPath().getList(".", Location.class);
        Assertions.assertEquals("Delhi", response.get(0).getLocalizedName());
        Assertions.assertEquals("India", response.get(0).getCountry().getLocalizedName());

    }
    @Test
    @DisplayName("Test Autocomplete check Type-City,FeyNumber and SPECIAL_RESPONSE_SIZE in China ")
    void testLocationsAsLocationCheckCountryID() {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", getApiKey());
        params.put("q", "De");
        List<Location> response = given().queryParams(params)
                .when().get(getBaseUrl() + "/locations/v1/cities/autocomplete")
                .then().statusCode(200).time(lessThan(1000L))
                .extract().body().jsonPath().getList(".", Location.class);
        Assertions.assertEquals("City", response.get(2).getType());
        Assertions.assertEquals("60631", response.get(2).getKey());
        Assertions.assertEquals(9, response.size() - 1);
    }
}

