package Project;

import java.io.IOException;
import java.sql.*;
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readings;
    }

    public int getLatestTempReadingFromDb() throws SQLException, IOException, ClassNotFoundException {
        int temp = 0;
        for(ValueReading vr : ListOfReadings()) {
            temp = vr.getTemperature();
        }
        return temp;
    }

    public int getLatestHumReadingFromDb() throws SQLException, IOException, ClassNotFoundException {
        int hum = 0;
        for(ValueReading vr : ListOfReadings()) {
            hum = vr.getHumidity();
        }
        return hum;
    }

    public int getLatestLumReadingFromDb() throws SQLException, IOException, ClassNotFoundException {
        int lum = 0;
        for(ValueReading vr : ListOfReadings()) {
            lum = vr.getTemperature();
        }
        return lum;
    }

    public ValueReading getEnergy(Date date) throws SQLException, IOException, ClassNotFoundException {
        for(ValueReading vr : ListOfReadings()) {
            if(vr.getCreatedDate() == date){
                return vr;
            }
        }
        return null;
    }
}
