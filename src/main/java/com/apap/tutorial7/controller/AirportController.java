package com.apap.tutorial7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.apap.tutorial7.rest.Setting;


@RestController

public class AirportController {
    @Autowired
    RestTemplate resTemplate;
    
    @Bean
    public RestTemplate res() {
    	return new RestTemplate();
    }
    
    @GetMapping(value ="/airports/{city}")
    public String getAirportByCity(@PathVariable("city") String city) throws Exception{
    	String path = Setting.airportUrl + city;
    	return resTemplate.getForEntity(path, String.class).getBody();
    }
}