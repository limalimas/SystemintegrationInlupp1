package Project;

import java.util.Date;

public class ValueReading {
    private int temperature;
    private int humidity;
    private int luminosity;
    private Date createdDate;
    private int id;



    private int energy;

    ValueReading(){ }


    public int getEnergy() { return energy; }

    public void setEnergy(int energy) { this.energy = energy; }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getLuminosity() { return luminosity; }

    public void setLuminosity(int luminosity) { this.luminosity = luminosity; }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
