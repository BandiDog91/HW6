import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpClient {
    public static void main(String[] args) {
        try{
            URL accuweather = new URL("http://dataservice.accuweather.com/forecasts/v1/daily/5day/28575?apikey=OcaeuGWrLxK9nNdOhZCayL4MrtbkMdpy ");
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
    }
}
