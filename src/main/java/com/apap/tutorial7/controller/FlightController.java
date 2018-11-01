package com.apap.tutorial7.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.service.FlightService;
import com.apap.tutorial7.service.PilotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * FlightController
 */
@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    public FlightService flightService;
    
    @Autowired
    public PilotService pilotService;
    
    @Bean
    public RestTemplate rest() {
    	return new RestTemplate();
    }

    @PostMapping(value = "/add")
    public FlightModel addFlightSubmit(@RequestBody FlightModel flight) {
        return flightService.addFlight(flight);
    }

    @GetMapping(value = "/view/{flightNumber}")
    public FlightModel flightView(@PathVariable("flightNumber") String flightNumber) {
        FlightModel archive = flightService.getFlightDetailByFlightNumber(flightNumber).get();
        return archive;
    }

    @DeleteMapping(value = "/delete/{flightId}")
    public String deleteFlight(@PathVariable("flightId") long flightId) {
        FlightModel flight = flightService.getFlightDetailById(flightId).get();
    	flightService.deleteFlight(flight);
    	return "flight has been deleted";
    }

    @PutMapping(value = "/update/{flightId}")
    public String updateFlight(@PathVariable("flightId") long flightId,
    		@RequestParam("destination") String destination, @RequestParam("origin") String origin,
    		@RequestParam("time") Date time) {
        FlightModel archive = flightService.getFlightDetailById(flightId).get();
        if(archive.equals(null)) {
    		return "Couldn't find your flight";
    	}
        archive.setDestination(destination);
        archive.setOrigin(origin);
        archive.setTime(time);
        flightService.updateFlight(flightId, archive);
    	return "flight update success";
    }

    @GetMapping(value = "/all")
    public List<FlightModel> allFlight() {
    	List<FlightModel> flightList = flightService.getAllFlight();
    	return flightList;
    }
}