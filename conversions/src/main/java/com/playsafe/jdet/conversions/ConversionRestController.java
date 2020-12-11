package com.playsafe.jdet.conversions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fact S Musingarimi
 * 11/12/2020
 * 11:24
 */
@RestController
@RequestMapping("conversions")
class ConversionRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConversionRestController.class);

    @GetMapping("/ctok")
    public Double celsiusToKelvin(@RequestParam double celsius) {
        LOGGER.info("Converting celsius to kelvin: {}", celsius);
        return celsius + 273.15;
    }

    @GetMapping("/ktoc")
    public Double kelvinToCelsius(@RequestParam double kelvin) {
        LOGGER.info("Converting kelvin to celsius: {}", kelvin);
        return kelvin - 273.15;
    }

    @GetMapping("/mtok")
    public Double milesToKilometers(@RequestParam double miles) {
        LOGGER.info("Converting miles to kilometers: {}", miles);
        return miles * 1.60934;
    }

    @GetMapping("/ktom")
    public Double kilometersToMiles(@RequestParam double kms) {
        LOGGER.info("Converting kilometers to celsius: {}", kms);
        return kms * 0.621371;
    }

}
