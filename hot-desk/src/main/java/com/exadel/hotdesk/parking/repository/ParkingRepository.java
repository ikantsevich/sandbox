package com.exadel.hotdesk.parking.repository;

import com.exadel.hotdesk.parking.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Long> {


}
