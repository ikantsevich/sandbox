package com.exadel.sandbox.parking.repository;

import com.exadel.sandbox.parking.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Long> {


}
