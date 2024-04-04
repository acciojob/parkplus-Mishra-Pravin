package com.driver.repository;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Integer>{
    Spot findById(int id);
    void deleteById(int id);
    Object findFirstByParkingLotAndNumberOfWheelsGreaterThanEqualOrderByPricePerHourAsc(ParkingLot parkingLot, int i);
}
