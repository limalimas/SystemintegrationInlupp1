package Project.Client;

import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    private static String getAllReadings() {
    final String url = "http://localhost:8080/all";
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(url, String.class);
    System.out.println(result);
    return result;
}

    private static String getCurrentReadingTemp() {
        final String url = "http://localhost:8080/current/temperature";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
        return result;
    }
    private static String getCurrentReadingHum() {
        final String url = "http://localhost:8080/current/humidity";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
        return result;
    }
    private static String getCurrentReadingLum() {
            final String url = "http://localhost:8080/current/luminosity";
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);
            System.out.println(result);
            return result;
    }
    private static String getCurrentEnergy() {
        final String url = "http://localhost:8080/energy/today";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
        return result;
    }

    public static void getCurrentMenu() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String choice;

        System.out.println("CURRENT");
        while (true) {
            System.out.println("1. Temperature\n2. Humidity\n3. Lumonosity\nVAL: ");

            choice = input.readLine();
            switch (choice) {
                case "1":
                    getCurrentReadingTemp();
                    break;
                case "2":
                    getCurrentReadingHum();
                    break;
                case "3":
                    getCurrentReadingLum();
                    break;
                case "4:":
                    getCurrentEnergy();
            }
            break;
        }
    }


    private static String getWeekTemp() {
        final String url = "http://localhost:8080/readings/week/temperature";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
        return result;
    }

    private static String getWeekHum() {
        final String url = "http://localhost:8080/readings/week/humidity";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
        return result;
    }

    private static String getWeekLum() {
        final String url = "http://localhost:8080/readings/week/light";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
        return result;
    }

    private static String getAll() {
        final String url = "http://localhost:8080/readings/week";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
        return result;
    }

    public static void rapportsMenu() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String choice;

        System.out.println("RAPPORTS");
        while (true) {
            System.out.println("1.All\n2.Temperature\n3.Humidity\n" +
                    "4.Luminosity\n5.Go Back\nChoice: ");

            choice = input.readLine();
            switch (choice) {
                case "1":
                    getAll();
                    break;
                case "2":
                    getWeekTemp();
                    break;
                case "3":
                    getWeekHum();
                    break;
                case "4":
                    getWeekLum();
                    break;
                case "5":
                    break;
            }
            break;
        }
    }

    public static void Menu() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String choice;

        System.out.println("GREENHOUSE");
        while(true) {
            System.out.println("\n1.Current \n2.Rapports\n3.Exit\nChoice:  ");
            choice = input.readLine();
            switch (choice) {
                case "1":
                    getCurrentMenu();
                    break;
                case "2":
                    rapportsMenu();
                    break;
                case "3":
                    System.exit(1);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Menu();
    }


}