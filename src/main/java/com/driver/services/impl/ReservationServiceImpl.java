package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SpotRepository spotRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new Exception("User not found");
        }

        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId);
        if (parkingLot == null) {
            throw new Exception("Parking lot not found");
        }

        List<Spot> availableSpots = spotRepository.findAll().stream()
                .filter(spot -> spot.getParkingLot().equals(parkingLot) && spot.getNumberOfWheels() >= numberOfWheels)
                .collect(Collectors.toList());

        if (availableSpots.isEmpty()) {
            throw new Exception("No spot is available for reservation");
        }

        Spot spotToReserve = availableSpots.get(0); // assuming the first spot is the best spot
        for (Spot spot : availableSpots) {
            if (spot.getPricePerHour() < spotToReserve.getPricePerHour()) {
                spotToReserve = spot;
            }
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setSpot(spotToReserve);
        reservation.setNumberOfHours(timeInHours);
        reservation.setTotalPrice(spotToReserve.getPricePerHour() * timeInHours);

        reservationRepository.save(reservation);

        return reservation;
    }
}