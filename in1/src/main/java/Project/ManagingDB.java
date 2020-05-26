package Project;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ManagingDB {

    private ArrayList<ValueReading> readings = new ArrayList<>();

    public ArrayList<ValueReading> ListOfReadings() throws IOException, ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        try (Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;database=Greenhouse;");
             PreparedStatement stmt = con.prepareStatement(
                     "select * from Measurements")) {

            ResultSet rs = stmt.executeQuery();
            readings.clear();
            while (rs.next()) {
                int id = rs.getInt("Id");
                int luminosity = rs.getInt("Luminosity");
                int temperature = rs.getInt("Temperature");
                int humidity = rs.getInt("Humidity");
                Date createdDate = rs.getDate("CreatedDate");

                ValueReading reading = new ValueReading();
                reading.setId(id);
                reading.setLuminosity(luminosity);
                reading.setTemperature(temperature);
                reading.setHumidity(humidity);
                reading.setCreatedDate(createdDate);
                readings.add(reading);

                System.out.println("Id: " + id);
                System.out.println("Luminosity: " + luminosity);
                System.out.println("Temperature: " + temperature);
                System.out.println("Humidity: " + humidity);
                System.out.println("Created: " + createdDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readings;
    }

    public ValueReading getLatestTempReadingFromDb() {

        return null;
    }

    public ValueReading getLatestHumReadingFromDb() {
        return null;
    }

    public ValueReading getLatestLumReadingFromDb() {
        return null;
    }

    public float getAverage(String temperature) {
        return 5;
    }

    public ArrayList<ValueReading> getReadingsFromDb() {
        return readings;
    }

    public int getEnergy(LocalDate date) {
        return 0;
    }

    public int getEnergy() {
        return 0;
    }
}
