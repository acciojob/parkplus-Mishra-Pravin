package com.driver.controllers;

import com.driver.services.impl.ReservationServiceImpl;
import com.driver.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    ReservationServiceImpl reservationService;

    @PostMapping("/reserveSpot")
    public Reservation reserveSpot(@RequestParam Integer userId, @RequestParam Integer parkingLotId, @RequestParam Integer timeInHours, @RequestParam Integer numberOfWheels) throws Exception{
        return reservationService.reserveSpot(userId, parkingLotId, timeInHours, numberOfWheels);
    }
}
