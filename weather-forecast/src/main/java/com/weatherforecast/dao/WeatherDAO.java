package main.java.com.weatherforecast.dao;

public interface WeatherDAO {

    public  void  AddWeatherForecast();
    public  void  removeWeatherForecast();
    public  void  updateWeatherForecast();

    public   void  getWeatherForecast();
    public  void  getWeatherForecastFilterByCountry();
    public  void  getWeatherForecastFilterByState();
    public void getCountries();
    public void getStates();

    public void  addCountry();
    public void  updateCountry();
    public void  deleteCountry();

    public void  addState();
    public void  updateState();
    public void  deleteState();

    public  boolean isAlpha(String str);





}
