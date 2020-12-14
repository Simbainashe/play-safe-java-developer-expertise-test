package com.playsafe.jdet.conversions;

/**
 * @author Fact S Musingarimi
 * 14/12/2020
 * 13:55
 */
public interface ConversionService {
    Double celsiusToKelvin(double celsius);

    Double kelvinToCelsius(double kelvin);

    Double milesToKilometers(double miles);

    Double kilometersToMiles(double kms);
}
