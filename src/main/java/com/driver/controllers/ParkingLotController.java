package com.driver.controllers;

import com.driver.services.ParkingLotService;
import com.driver.services.impl.ParkingLotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.driver.model.*;

@RestController
@RequestMapping("/parking-lots")
public class ParkingLotController {

    @Autowired
    ParkingLotServiceImpl parkingLotService;

    @PostMapping("/add")
    public ResponseEntity<ParkingLot> addParkingLot(@RequestParam String name, @RequestParam String address) {
        ParkingLot newParkingLot = parkingLotService.addParkingLot(name, address);
        return new ResponseEntity<>(newParkingLot, HttpStatus.CREATED);
    }

    @PostMapping("/{parkingLotId}/spot/add")
    public ResponseEntity<Spot> addSpot(@PathVariable int parkingLotId, @RequestParam Integer numberOfWheels, @RequestParam Integer pricePerHour) {
        Spot newSpot = parkingLotService.addSpot(parkingLotId, numberOfWheels, pricePerHour);
        return new ResponseEntity<>(newSpot, HttpStatus.CREATED);
    }

    @DeleteMapping("/spot/{spotId}/delete")
    public ResponseEntity<Void> deleteSpot(@PathVariable int spotId) {
        parkingLotService.deleteSpot(spotId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{parkingLotId}/spot/{spotId}/update")
    public ResponseEntity<Spot> updateSpot(@PathVariable int parkingLotId, @PathVariable int spotId, @RequestParam int pricePerHour) {
        Spot updatedSpot = parkingLotService.updateSpot(parkingLotId, spotId, pricePerHour);
        return new ResponseEntity<>(updatedSpot, HttpStatus.OK);
    }

    @DeleteMapping("/{parkingLotId}/delete")
    public ResponseEntity<Void> deleteParkingLot(@PathVariable int parkingLotId) {
        parkingLotService.deleteParkingLot(parkingLotId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
