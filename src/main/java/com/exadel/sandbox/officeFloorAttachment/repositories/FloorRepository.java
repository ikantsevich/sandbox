package com.exadel.sandbox.officeFloorAttachment.repositories;

import com.exadel.sandbox.officeFloorAttachment.entities.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
    List<Floor> getAllByOfId(Long id);
}
