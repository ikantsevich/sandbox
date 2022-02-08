package com.exadel.sandbox.repositories;

import com.exadel.sandbox.entities.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FloorRepository extends JpaRepository<Floor, Long> {
    List<Floor> getAllByOfId(Long id);
}
