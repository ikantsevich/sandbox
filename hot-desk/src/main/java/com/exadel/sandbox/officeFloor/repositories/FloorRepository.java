package com.exadel.sandbox.officeFloor.repositories;

import com.exadel.sandbox.attachment.entity.Attachment;
import com.exadel.sandbox.officeFloor.entities.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
    List<Floor> findFloorsByOfficeId(Long id);
}
