package com.playsafe.jdet.conversions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Fact S Musingarimi
 * 14/12/2020
 * 13:57
 */
@Service
class ConversionServiceImpl implements ConversionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConversionServiceImpl.class);

    public Double celsiusToKelvin(double celsius) {
        LOGGER.info("Converting celsius to kelvin: {}", celsius);
        return celsius + 273.15;
    }

    @Override
    public Double kelvinToCelsius(double kelvin) {
        LOGGER.info("Converting kelvin to celsius: {}", kelvin);
        return kelvin - 273.15;
    }

    @Override
    public Double milesToKilometers(double miles) {
        LOGGER.info("Converting miles to kilometers: {}", miles);
        return miles * 1.60934;
    }

    @Override
    public Double kilometersToMiles(double kms) {
        LOGGER.info("Converting kilometers to celsius: {}", kms);
        return kms * 0.621371;
    }

}
