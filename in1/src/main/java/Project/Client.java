package Project;

import org.springframework.web.client.RestTemplate;

public class Client {

    private static String getAllReadings() {
        final String url = "http://localhost:8080/all";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
        return result;
    }
}