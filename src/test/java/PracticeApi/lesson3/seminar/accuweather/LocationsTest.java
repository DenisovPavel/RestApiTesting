package PracticeApi.lesson3.seminar.accuweather;

import PracticeApi.lesson3.seminar.accuweather.location.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class LocationsTest extends AccuweatherAbstractTest {
    @Test
    @DisplayName("Test Autocomplete City-Name")
    void testLocationsAsString() {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", getApiKey());
        params.put("q", "Pari");
        String response = given().queryParams(params)
                .when().get(getBaseUrl() + "/locations/v1/cities/autocomplete")
                .then().statusCode(200).time(lessThan(1000L))
                .extract().asString();
        System.out.println(response);
    }

    @Test
    @DisplayName("Test Autocomplete City-Name")
    void testLocationsAsLocation() {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", getApiKey());
        params.put("q", "Pari");
        List<Location> response = given().queryParams(params)
                .when().get(getBaseUrl() + "/locations/v1/cities/autocomplete")
                .then().statusCode(200).time(lessThan(1000L))
                .extract().body().jsonPath().getList(".", Location.class);
        Assertions.assertEquals(10, response.size());
        Assertions.assertEquals("Paris",response.get(0).getLocalizedName());
        Assertions.assertEquals("France",response.get(0).getCountry().getLocalizedName());
    }
}
