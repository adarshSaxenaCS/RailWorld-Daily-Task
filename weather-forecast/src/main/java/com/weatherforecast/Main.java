package main.java.com.weatherforecast;
import main.java.com.weatherforecast.dao.WeatherDAO;
import main.java.com.weatherforecast.service.WeatherService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final  WeatherDAO  weatherDAO=new WeatherService();
            while (true) {
                System.out.println("1. View Weather Forecast ");
                System.out.println("   1.1  Add Weather Forecast");
                System.out.println("   1.2  Update Weather Forecast");
                System.out.println("   1.3  Remove Weather Forecast");
                System.out.println("   1.4  Filter by Country ");
                System.out.println("   1.5  Filter by State ");

                System.out.println("2. View County Forecast ");
                System.out.println("   2.1 Add County Forecast ");
                System.out.println("   2.2 Update County Forecast ");
                System.out.println("   2.3 Remove County Forecast ");
                System.out.println("3. View State Forecast ");
                System.out.println("   3.1 Add State Forecast ");
                System.out.println("   3.2 Update State Forecast ");

                System.out.println("   3.3 Remove State Forecast ");
                System.out.println("4. Exit ");
                System.out.print("Enter your choice: ");
                int selectOption = 0;
                Scanner input = new Scanner(System.in);
                try {
                selectOption = input.nextInt();
                }catch (Exception e)
                {
                    input.nextLine();
                    System.out.println("Invalid option , choice (1,2,3,4,11,12,13,14,15,21,22,23,31,32,33)");
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
                }

                if (selectOption == 1)  weatherDAO.getWeatherForecast();
                else  if (selectOption==11) weatherDAO.AddWeatherForecast();
                else if (selectOption==12) weatherDAO.updateWeatherForecast();
                else if (selectOption==13) weatherDAO.removeWeatherForecast();
                else if (selectOption == 14) weatherDAO.getWeatherForecastFilterByCountry();
                else if (selectOption == 15) weatherDAO.getWeatherForecastFilterByState();

                else if (selectOption == 2)weatherDAO.getCountries();
                else if (selectOption==21)weatherDAO.addCountry();
                else if (selectOption==22)weatherDAO.updateCountry();
                else if(selectOption==23)weatherDAO.deleteCountry();
                else if(selectOption==23)weatherDAO.deleteCountry();

                else if (selectOption == 3) weatherDAO.getStates();
                else if (selectOption==31)weatherDAO.addState();
                else if(selectOption==32)weatherDAO.updateState();
                else if(selectOption==33)weatherDAO.deleteState();

                else if (selectOption == 4) break;
                else System.out.println("Invalid option , choice (1,2,3,4,11,12,13,14,15,21,22,23,31,32,33)"); System.out.println("-------------------------------------------------------------------------------------------------------------------------------");

            }
    }
}