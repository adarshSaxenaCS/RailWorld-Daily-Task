package main.java.com.weatherforecast.service;
import main.java.com.weatherforecast.dao.WeatherDAO;
import main.java.com.weatherforecast.util.DatabaseUtil;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class WeatherService implements WeatherDAO {
    Scanner scanner = new Scanner(System.in);
    private Connection con = null;

    @Override
    public void getWeatherForecast() {
        try {
            con = DatabaseUtil.getConnection();
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT   weatherforecast.ForecastID ,weatherforecast.ForecastDate,country.CountryName,state.StateName,weatherforecast.PredictedTemperature,weatherforecast.PredictedHumidity,weatherforecast.PredictedWeatherCondition FROM  weatherforecast,state,country where weatherforecast.StateID=state.StateID and country.CountryID=state.CountryID");
            // Print header
            System.out.printf("%-12s %-12s %-12s %-20s %-18s %-18s %-25s\n", "Forecast ID", "Date", "Country", "State", "Temperature °C", "Humidity %", "Condition");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            // Print each row
            while (resultSet.next()) {
                System.out.printf("%-12s %-12s %-12s %-20s %s %-12.5s %s %-12.2s %-20s\n",
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        "°C",
                        resultSet.getString(6),
                        "%",
                        resultSet.getString(7));
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getWeatherForecastFilterByCountry() {
        System.out.println("Enter your country : ");
        String cnty = scanner.next();
        try {
            con = DatabaseUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT  weatherforecast.ForecastID,weatherforecast.ForecastDate,country.CountryName,state.StateName,weatherforecast.PredictedTemperature,weatherforecast.PredictedHumidity,weatherforecast.PredictedWeatherCondition FROM  weatherforecast,state,country where weatherforecast.StateID=state.StateID and country.CountryID=state.CountryID and country.CountryName=?");
            pstmt.setString(1, cnty);
            ResultSet resultSet = pstmt.executeQuery();
            System.out.printf("%-12s %-12s %-12s %-20s %-18s %-18s %-25s\n", "Forecast ID", "Date", "Country", "State", "Temperature °C", "Humidity %", "Condition");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            while (resultSet.next()) {
                System.out.printf("%-12s %-12s %-12s %-20s %s %-12.5s %s %-12.2s %-20s\n",
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        "°C",
                        resultSet.getString(6),
                        "%",
                        resultSet.getString(7));
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getWeatherForecastFilterByState() {
        System.out.println("Enter your state : ");
        String cnty = scanner.next();
        try {
            con = DatabaseUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT  weatherforecast.ForecastID,weatherforecast.ForecastDate,country.CountryName,state.StateName,weatherforecast.PredictedTemperature,weatherforecast.PredictedHumidity,weatherforecast.PredictedWeatherCondition FROM  weatherforecast,state,country where weatherforecast.StateID=state.StateID and country.CountryID=state.CountryID and state.StateName=?");
            pstmt.setString(1, cnty);
            ResultSet resultSet = pstmt.executeQuery();
            System.out.printf("%-12s %-12s %-12s %-20s %-18s %-18s %-25s\n", "Forecast ID", "Date", "Country", "State", "Temperature °C", "Humidity %", "Condition");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            while (resultSet.next()) {
                System.out.printf("%-12s %-12s %-12s %-20s %s %-12.5s %s %-12.2s %-20s\n",
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        "°C",
                        resultSet.getString(6),
                        "%",
                        resultSet.getString(7));
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getCountries() {
        con = DatabaseUtil.getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM railworldproject.country order by CountryID");
            System.out.printf("%-12s %s\n", "Country ID", "Country Name");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            while (resultSet.next()) {
                System.out.printf("%-12s %s\n", resultSet.getString(1), resultSet.getString(2));
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getStates() {
        con = DatabaseUtil.getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM railworldproject.state;");
            System.out.printf("%-12s %s\n", "State ID", "State Name");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");

            while (resultSet.next()) {
                System.out.printf("%-12s %s\n", resultSet.getString(1), resultSet.getString(2));
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCountry() {


        System.out.print("Enter your country : ");
        String country = scanner.next();
        if(!isAlpha(country))
        {
            System.out.println("Invalid country entered");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            scanner.nextLine();
            return;
        }

        country = country.toLowerCase();
        try {
            con = DatabaseUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT  country.CountryName FROM country where country.CountryName=?");
            pstmt.setString(1, country);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                System.out.println("Country Exist  !!");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            } else {
                pstmt = con.prepareStatement("INSERT INTO Country (CountryName) VALUES(?)");
                pstmt.setString(1, country);
                pstmt.executeUpdate();
                System.out.println("Country successfully added !!");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCountry() {
        System.out.print("Enter your country Id : ");
        int countryId=0;
        try {
        countryId = scanner.nextInt();
        }catch (Exception e)
        {
            System.out.println("Invalid input entered");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            scanner.nextLine();
            return;
        }
        try {
            con = DatabaseUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select * from country where CountryID=?");
            pstmt.setInt(1, countryId);
            ResultSet resultSet = pstmt.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Country Id Not  Exist  !!");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Enter your country : ");
                String country = scanner.next();
                if(!isAlpha(country))
                {
                    System.out.println("Invalid country entered");
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
                    return;
                }

                country = country.toLowerCase();

                pstmt = con.prepareStatement("update country set CountryName=? where CountryID=?");
                pstmt.setString(1, country);
                pstmt.setInt(2, countryId);
                pstmt.executeUpdate();
                System.out.println("Country updated !!");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCountry() {
        System.out.print("Enter your country Id : ");
        int countryId =0;
        try {
            countryId = scanner.nextInt();
        }catch (Exception e)
        {

            System.out.println("Invalid input entered");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            scanner.nextLine();
            return;
        }

        try {
            con = DatabaseUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select * from country where CountryID=?");
            pstmt.setInt(1, countryId);
            ResultSet resultSet = pstmt.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Country Id Not  Exist  !!");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            } else {
                pstmt = con.prepareStatement("delete from country where CountryID=?");
                pstmt.setInt(1, countryId);
                pstmt.executeUpdate();
                System.out.println("Country removed !!");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addState() {
        System.out.println("Enter your state name : ");
        String state = scanner.nextLine();
        if(!isAlpha(state))
        {
            System.out.println("Invalid state entered");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            return;
        }
        state = state.toLowerCase();
        int countryId=0;
        double latitude=0;
        double longitude=0;
        try {
            System.out.println("Enter your country Id : ");
            countryId = scanner.nextInt();
            System.out.println("Enter your Latitude ");
            latitude = scanner.nextDouble();
            System.out.println("Enter your Longitude ");
            longitude = scanner.nextDouble();
        }catch (Exception e)
        {

            System.out.println("Invalid input entered");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            scanner.nextLine();
            return;
        }


        try {
            con = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("select * from country where CountryID=?");
            preparedStatement.setInt(1, countryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Country Id Not Exist  !!");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            } else {
                preparedStatement = con.prepareStatement("INSERT INTO State (StateName, CountryID, Latitude, Longitude) VALUES(?,?,?,?)");
                preparedStatement.setString(1, state);
                preparedStatement.setInt(2, countryId);
                preparedStatement.setDouble(3, latitude);
                preparedStatement.setDouble(4, longitude);
                preparedStatement.executeUpdate();
                System.out.println("State added !!");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateState() {

        int stateId=0;
        try {
        System.out.println("Enter your state Id : ");
        stateId = scanner.nextInt();
        }catch (Exception e)
        {
            System.out.println("Invalid input entered");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            scanner.nextLine();
            return;
        }


        try {
            con = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("select * from state where StateID=?");
            preparedStatement.setInt(1, stateId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("State Id Not Exist  !!");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
                return;
            }
            scanner.nextLine();

            int countryId=0;
            double latitude=0;
            double longitude=0;

            String state = null;
            try {
                System.out.println("Enter your state name : ");
                state = scanner.nextLine();
                state = state.toLowerCase();
                System.out.println("Enter your country Id : ");
                countryId = scanner.nextInt();
                System.out.println("Enter your Latitude ");
                latitude = scanner.nextDouble();
                System.out.println("Enter your Longitude ");
                longitude = scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Invalid input entered");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
               scanner.nextLine();
                return;
            }
            preparedStatement = con.prepareStatement("select * from country where CountryID=?");
            preparedStatement.setInt(1, countryId);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Country Id Not Exist  !!");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            } else {
                preparedStatement = con.prepareStatement("update  State  SET StateName=? ,CountryID=? ,Latitude=? ,Longitude=? WHERE StateID=?");
                preparedStatement.setString(1, state);
                preparedStatement.setInt(2, countryId);
                preparedStatement.setDouble(3, latitude);
                preparedStatement.setDouble(4, longitude);
                preparedStatement.setInt(5, stateId);
                preparedStatement.executeUpdate();
                System.out.println("State Updated !!");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteState() {

        int stateId = 0;
        try {
            System.out.println("Enter your State Id : ");
            stateId = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input entered");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            scanner.nextLine();
            return;
        }


        try {
            con = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("select * from state where StateID=?");
            preparedStatement.setInt(1, stateId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("State Id Not Exist  !!");
            } else {

                preparedStatement = con.prepareStatement("Select StateID from weatherforecast where StateID=?");
                preparedStatement.setInt(1, stateId);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    System.out.println("Cannot delete or update the record: It is referenced by another record.");
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
                    return;
                }
                preparedStatement = con.prepareStatement("DELETE FROM state WHERE StateID =?");
                preparedStatement.setInt(1, stateId);
                preparedStatement.executeUpdate();
                System.out.println("State Removed  !!");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void AddWeatherForecast() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        int stateId = 0;
        double temperature = 0;
        double humidity = 0;
        String condition = null;
        try {
            System.out.println("Enter your state Id : ");
            stateId = scanner.nextInt();
            System.out.println("Enter your Predicted Temperature : ");
            temperature = scanner.nextDouble();
            System.out.println("Enter your Predicted Humidity (1 to 100 %) : ");
            humidity = scanner.nextDouble();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid input entered ");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            return;
        }
            scanner.nextLine();
            System.out.println("Enter your Predicted Predicted Weather Condition : ");
            condition = scanner.nextLine();
            if(!isAlpha(condition))
            {
                System.out.println("Predicted Predicted Weather Condition is not alphabetic");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
                return;
            }
           condition = condition.toLowerCase();

        try {
            con = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("select * from state where StateID=?");
            preparedStatement.setInt(1, stateId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("State Id Not Exist  !!");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
                return;
            }
            preparedStatement = con.prepareStatement("INSERT INTO WeatherForecast (ForecastDate, StateID, PredictedTemperature, PredictedHumidity, PredictedWeatherCondition) VALUES(?,?,?,?,?)");
            preparedStatement.setString(1, formattedDate);
            preparedStatement.setInt(2, stateId);
            preparedStatement.setDouble(3, temperature);
            preparedStatement.setDouble(4, humidity);
            preparedStatement.setString(5, condition);
            preparedStatement.executeUpdate();
            System.out.println("Weather Added !!");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeWeatherForecast() {

        int forecastID = 0;
        try {
            System.out.println("Enter your Weather Forecast  Id : ");
            forecastID = scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid input entered");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            return;
        }

        try {
            con = DatabaseUtil.getConnection();

            PreparedStatement preparedStatement = con.prepareStatement("select * from weatherforecast where ForecastID=?");
            preparedStatement.setInt(1, forecastID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Weather Forecast Id Not Exist  !!");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
                return;
            }
            preparedStatement = con.prepareStatement("DELETE FROM WeatherForecast WHERE ForecastID=?");
            preparedStatement.setInt(1, forecastID);
            preparedStatement.executeUpdate();
            System.out.println("Weather Removed !!");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateWeatherForecast() {

        int forecastID = 0;
        int stateId = 0;
        double temperature = 0;
        int humidity = 0;
        try {
            System.out.println("Enter your Weather Forecast  Id : ");
            forecastID = scanner.nextInt();
            System.out.println("Enter your state Id : ");
            stateId = scanner.nextInt();
            System.out.println("Enter your Predicted Temperature : ");
            temperature = scanner.nextDouble();
            System.out.println("Enter your Predicted Humidity (1 to 100 %) : ");
            humidity = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid input entered");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            return;
        }
        System.out.println("Enter your Predicted Predicted Weather Condition : ");
        String condition = scanner.nextLine();
        if (!isAlpha(condition))
        {
            System.out.println("Predicted Predicted Weather Condition is not alphabetic");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
            return;
        }

        condition = condition.toLowerCase();
        try {
            con = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement("select * from weatherforecast where ForecastID=?");
            preparedStatement.setInt(1, forecastID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Weather Forecast Id Not Exist  !!");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
                return;
            }
            preparedStatement = con.prepareStatement("select * from state where StateID=?");
            preparedStatement.setInt(1, stateId);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("State Id Not Exist  !!");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
                return;
            }
            preparedStatement = con.prepareStatement("UPDATE weatherforecast SET StateID = ?, PredictedTemperature = ?, PredictedHumidity = ?, PredictedWeatherCondition = ? WHERE ForecastID = ?");
            preparedStatement.setInt(1, stateId);
            preparedStatement.setDouble(2, temperature);
            preparedStatement.setInt(3, humidity);
            preparedStatement.setString(4, condition);
            preparedStatement.setInt(5, forecastID);
            preparedStatement.executeUpdate();
            System.out.println("Weather Updated !!");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  boolean isAlpha(String str)
    {
        String regex = "[a-zA-Z]+";
        return  str.matches(regex);
    }



}
