import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpClient {
    public static void main(String[] args) {

        String forecastJson = load5DayForecastOrNull();

        if (forecastJson != null) {
            StringReader forecastJsonReader = new StringReader(forecastJson);
            JsonReader jsonReader = Json.createReader(forecastJsonReader);
            JsonObject weatherResponseJson = jsonReader.readObject();
            WeatherResponse weatherResponse = new WeatherResponse(weatherResponseJson);
            System.out.println(weatherResponse);
        } else {
            System.out.println("Не удалось прочитать данные с сервера.");
        }
    }
        public static String load5DayForecastOrNull() {
            try{
                URL accuweather = new URL("http://dataservice.accuweather.com/forecasts/v1/daily/5day/28575?apikey=OcaeuGWrLxK9nNdOhZCayL4MrtbkMdpy&metric=true ");
                HttpURLConnection urlConnection = (HttpURLConnection) accuweather.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                        StringBuilder responseContent = new StringBuilder();
                        String line = "";
                        while ((line = reader.readLine()) != null) {
                            responseContent.append(line);
                        }
                        System.out.println(responseContent);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            return null;
        }
}
