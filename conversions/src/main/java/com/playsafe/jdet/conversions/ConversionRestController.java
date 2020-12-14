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
    private final ConversionService conversionService;

    ConversionRestController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping("/ctok")
    public Double celsiusToKelvin(@RequestParam double celsius) {
        LOGGER.info("Conversion request for celsius to kelvin: {}", celsius);
        return conversionService.celsiusToKelvin(celsius);
    }

    @GetMapping("/ktoc")
    public Double kelvinToCelsius(@RequestParam double kelvin) {
        LOGGER.info("Conversion request for kelvin to celsius: {}", kelvin);
        return conversionService.kelvinToCelsius(kelvin);
    }

    @GetMapping("/mtok")
    public Double milesToKilometers(@RequestParam double miles) {
        LOGGER.info("Conversion request for miles to kilometers: {}", miles);
        return conversionService.milesToKilometers(miles);
    }

    @GetMapping("/ktom")
    public Double kilometersToMiles(@RequestParam double kms) {
        LOGGER.info("Conversion request for kilometers to celsius: {}", kms);
        return conversionService.kilometersToMiles(kms);
    }

}
