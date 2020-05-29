package Project.Controller;
import Project.ManagingDB;
import Project.ValueReading;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Objects;


@RestController
public class Controller {

    ManagingDB db = new ManagingDB();

    @RequestMapping("/all")
    public ArrayList<ValueReading> getReadingsFromDb() throws IOException, ClassNotFoundException, SQLException {
        return db.ListOfReadings(); }

    @RequestMapping("/current/{type}")
    public int getTypeReadingFromDb(@PathVariable int type) throws SQLException, IOException, ClassNotFoundException {
        if(Objects.equals(type, "temperature")){
            return db.getLatestTempReadingFromDb();
        }
        else if(Objects.equals(type, "humidity")){
            return db.getLatestHumReadingFromDb();
        }
        else if(Objects.equals(type, "luminosity")){
            return db.getLatestLumReadingFromDb();
        }
        return 0;
    }

    @RequestMapping("/all/{type}")
    public String getTypeReadingFromDb(@PathVariable String type) throws SQLException, IOException, ClassNotFoundException {
        if (type.equals("temperature")) {
            String s = "";
            for (ValueReading measure : db.ListOfReadings()) {
                s = measure.getCreatedDate() + " - Temperature: " + measure.getTemperature() + "/n";
            }
            return s;
        } else if (type.equals("humidity")) {
            String s = "";
            for (ValueReading measure : db.ListOfReadings()) {
                s = measure.getCreatedDate() + " - Humidity: " + measure.getHumidity() + "/n";
            }
            return s;

        } else if (type.equals("luminosity")) {
            String s = "";

            for (ValueReading measure : db.ListOfReadings()) {
                s = measure.getCreatedDate() + " - Luminosity: " + measure.getLuminosity() + "/n";
            }
            return s;
        }

        return type;
    }
    @RequestMapping("/readings/week/{type}")
    public String weekly (@PathVariable String type) throws SQLException, IOException, ClassNotFoundException {
        if (type.equals("temperature")) {
            double total = 0;
            String res = "";
            for (int i = 1; i < 8; i++) {
                ValueReading vr = db.ListOfReadings().get(db.ListOfReadings().size() - i);
                total += vr.getTemperature();
                res += "Id: " + vr.getId() + ", temperatur vid avläsning: " + vr.getTemperature() + "°C, datum: " + vr.getCreatedDate() + "\n";
            }
            String avg = String.format("%.2f", (total / 7));

            res = "\nSnitt: " + avg + "°C";
            return res;
        }
        if (type.equals("humidity")) {
            double total = 0;
            String res = "";
            for (int i = 1; i < 8; i++) {
                ValueReading vr = db.ListOfReadings().get(db.ListOfReadings().size() - i);
                total += vr.getHumidity();
                res += "Id: " + vr.getId() + ", luftfuktighet vid avläsning: " + vr.getHumidity() + "%, datum: " + vr.getCreatedDate() + "\n";
            }
            String avg = String.format("%.2f", (total / 7));

            res = "\nSnitt: " + avg +"%";
            return res;
        }
        if (type.equals("luminosity")) {
            double total = 0;
            String res = "";
            for (int i = 1; i < 8; i++) {
                ValueReading vr = db.ListOfReadings().get(db.ListOfReadings().size() - i);
                total += vr.getLuminosity();
                res += "Id: " + vr.getId() + ", ljusstyrka vid avläsning: " + vr.getLuminosity() + " datum: " + vr.getCreatedDate() + "\n";
            }
            String avg = String.format("%.2f", (total / 7));

            res = "\nSnitt: " + avg;
            return res;
        }
        return null;
    }
    @RequestMapping("/readings/week")
    public String weeklyAvg () throws SQLException, IOException, ClassNotFoundException {
        System.out.println(db.ListOfReadings().size());
        String res = "";
        for(int i = 1; i < 8; i++){
            ValueReading vr = db.ListOfReadings().get(db.ListOfReadings().size() - i);
            res = res.concat("Id: " + vr.getId() + ", datum: " + vr.getCreatedDate() + ", temperatur: " + vr.getTemperature() + "°C, luftfuktighet: "
                    + vr.getHumidity() + "%, ljusstyrka: " + vr.getLuminosity());
        }
        return res;
    }

    @RequestMapping("/energy/today")
    public int getEnergyToday() throws SQLException, IOException, ClassNotFoundException {
        int le =0;
        for(ValueReading vr : db.ListOfReadings()){
            le = vr.getEnergy();
        }
        return le;
    }
    @RequestMapping("/energy/{date}")
    public ValueReading getEnergy(@PathVariable Date date) throws SQLException, IOException, ClassNotFoundException {
        return db.getEnergy(date);
    }
    @RequestMapping("/energyCost/{cost}")
    public int getEnergy(@PathVariable int cost) throws SQLException, IOException, ClassNotFoundException {
        int total=0;
        for(int i = 1; i < 8; i++) {
            ValueReading vr = db.ListOfReadings().get(db.ListOfReadings().size() - i);
            total += vr.getEnergy();
        }
        return total * cost;
    }

}