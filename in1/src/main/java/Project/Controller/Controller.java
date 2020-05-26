package Project.Controller;
import Project.ManagingDB;
import Project.ValueReading;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


@RestController
public class Controller {

    ManagingDB db = new ManagingDB();

    @RequestMapping("/all")
    public ArrayList<ValueReading> getReadingsFromDb() throws IOException, ClassNotFoundException, SQLException {
        return db.ListOfReadings(); }

    @RequestMapping("/current/{type}")
    public ValueReading getTypeReadingFromDb(@PathVariable int type) {
        if(Objects.equals(type, "temperature")){
            return db.getLatestTempReadingFromDb();
        }
        else if(Objects.equals(type, "humidity")){
            return db.getLatestHumReadingFromDb();
        }
        else if(Objects.equals(type, "luminosity")){
            return db.getLatestLumReadingFromDb();
        }
        return null;
    }

    @RequestMapping("/all/{type}")
    public String getTypeReadingFromDb(@PathVariable String type) {
        if (type.equals("temperature")) {
            String s = "";
            float average = db.getAverage("temperature");
            for (ValueReading measure : db.getReadingsFromDb()) {
                s = measure.getCreatedDate() + " - Temperature: " + measure.getTemperature() + "/n";
            }
            return s + "Average: " + average;
        } else if (type.equals("humidity")) {
            String s = "";
            float average = db.getAverage("humidity");
            for (ValueReading measure : db.getReadingsFromDb()) {
                s = measure.getCreatedDate() + " - Humidity: " + measure.getHumidity() + "/n";
            }
            return s + "Average: " + average;

        } else if (type.equals("luminosity")) {
            String s = "";
            float average = db.getAverage("luminosity");
            for (ValueReading measure : db.getReadingsFromDb()) {
                s = measure.getCreatedDate() + " - Luminosity: " + measure.getLuminosity() + "/n";
            }
            return s + "Average: " + average;
        }

        return type;
    }

    @RequestMapping("/energy/{date}")
    public int getEnergy(@PathVariable LocalDate date) {
        return db.getEnergy(date);
    }
    @RequestMapping("/energyCost/{cost}")
    public int getEnergy(@PathVariable int cost) {
        int energy = db.getEnergy();
        return energy * cost;
    }

}