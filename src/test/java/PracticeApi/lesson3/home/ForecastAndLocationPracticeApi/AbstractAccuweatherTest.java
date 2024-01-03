package PracticeApi.lesson3.home.ForecastAndLocationPracticeApi;

import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

abstract public class AbstractAccuweatherTest {
    static Properties properties = new Properties();
    private static InputStream configFile;
    private static String apiKey; // Api
    private static String baseUrl; //Base Http url

    @BeforeAll
    static void initConfig() throws IOException { // If file not found we get IOException;
        configFile = new FileInputStream("src/test/resources/accuweather.properties");// подтянули configFile
        properties.load(configFile); // загрузили configFile в properties;
        apiKey = properties.getProperty("apikey");
        baseUrl = properties.getProperty("base_url");
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}
