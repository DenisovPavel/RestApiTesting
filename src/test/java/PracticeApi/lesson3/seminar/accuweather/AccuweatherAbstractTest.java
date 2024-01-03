package PracticeApi.lesson3.seminar.accuweather;

import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

abstract public class AccuweatherAbstractTest {
    static Properties properties = new Properties();// Стандартный класс который обрабатывает properties из resources
    private static InputStream configFile;// стандартный класс, с его помощью возможно читать данные из файла.
    private static String apiKey; // Api
    private static String baseUrl; //Base Http address

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
