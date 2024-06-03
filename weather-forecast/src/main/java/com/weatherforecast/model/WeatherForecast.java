package main.java.com.weatherforecast.model;

public class WeatherForecast {
    private String date;
    private String country;
    private String state;
    private int temperature;
    private int humidity;
    private String Condition;

    public WeatherForecast(String date, String country, String state, int temperature, int humidity, String condition) {
        this.date = date;
        this.country = country;
        this.state = state;
        this.temperature = temperature;
        this.humidity = humidity;
        Condition = condition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

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

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "date='" + date + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", Condition='" + Condition + '\'' +
                '}';
    }
}
