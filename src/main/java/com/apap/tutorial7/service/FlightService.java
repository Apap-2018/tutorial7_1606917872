package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;

/**
 * FlightService
 */
public interface FlightService {
    FlightModel addFlight(FlightModel flight);
    void updateFlight(long id, FlightModel flight);
    void deleteByFlightNumber(String flightNumber);
    void deleteFlight(FlightModel flight);
    Optional<FlightModel> getFlightDetailByFlightNumber(String flightNumber);
    Optional<FlightModel> getFlightDetailById(long id);
    List<FlightModel> getAllFlight();
}