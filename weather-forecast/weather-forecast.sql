CREATE TABLE Country (
    CountryID INT AUTO_INCREMENT PRIMARY KEY,
    CountryName VARCHAR(50) NOT NULL
);

CREATE TABLE State (
    StateID INT AUTO_INCREMENT PRIMARY KEY,
    StateName VARCHAR(50) NOT NULL,
    CountryID INT NOT NULL,
    Latitude DECIMAL(8, 5) NOT NULL,
    Longitude DECIMAL(8, 5) NOT NULL,
    FOREIGN KEY (CountryID) REFERENCES Country(CountryID)
);

CREATE TABLE WeatherForecast (
    ForecastID INT AUTO_INCREMENT PRIMARY KEY,
    ForecastDate DATE NOT NULL,
    StateID INT NOT NULL,
    PredictedTemperature DECIMAL(5,2) NOT NULL,
    PredictedHumidity DECIMAL(5,2) NOT NULL,
    PredictedWeatherCondition VARCHAR(50) NOT NULL,
    FOREIGN KEY (StateID) REFERENCES State(StateID)
);


INSERT INTO Country (CountryName) VALUES
('India'),
('Pakistan'),
('Nepal'),
('Bangladesh'),
('Sri Lanka'),
('Bhutan'),
('Myanmar'),
('Maldives');

INSERT INTO State (StateName, CountryID, Latitude, Longitude) VALUES
('Maharashtra', 1, 19.0760, 72.8777),
('Gujarat', 1, 23.0225, 72.5714),
('Punjab', 2, 31.5497, 74.3436),
('Sindh', 2, 24.8607, 67.0011),
('Bagmati', 3, 27.7172, 85.3240),
('Chittagong', 4, 22.3569, 91.7832),
('Dhaka', 4, 23.8103, 90.4125),
('Western Province', 5, 6.9271, 79.8612),
('Thimphu', 6, 27.4712, 89.6339),
('Yangon', 7, 16.8409, 96.1735),
('Malé', 8, 4.1755, 73.5093);

INSERT INTO WeatherForecast (ForecastDate, StateID, PredictedTemperature, PredictedHumidity, PredictedWeatherCondition) VALUES
('2024-06-01', 1, 29.00, 70.00, 'Sunny'),
('2024-06-02', 2, 32.00, 65.00, 'Partly Cloudy'),
('2024-06-03', 3, 33.00, 55.00, 'Sunny'),
('2024-06-04', 4, 34.00, 50.00, 'Sunny'),
('2024-06-05', 5, 24.00, 75.00, 'Rain'),
('2024-06-06', 6, 30.00, 80.00, 'Cloudy'),
('2024-06-07', 7, 31.00, 85.00, 'Cloudy'),
('2024-06-08', 8, 28.00, 90.00, 'Rain'),
('2024-06-09', 9, 22.00, 60.00, 'Sunny'),
('2024-06-10', 10, 35.00, 55.00, 'Sunny'),
('2024-06-11', 11, 31.00, 70.00, 'Partly Cloudy');



