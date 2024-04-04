package com.driver.services;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;

public interface ParkingLotService {
    ParkingLot addParkingLot(String name, String address);
    Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour);
    Spot updateSpot(int parkingLotId, int spotId, int pricePerHour);
    void deleteSpot(int spotId);
    void deleteParkingLot(int parkingLotId);
}
