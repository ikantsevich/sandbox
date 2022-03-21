package com.exadel.sandbox.vacation.repository;

import com.exadel.sandbox.vacation.entities.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Long> {
    List<Vacation> findAllByEmployeeId(Long id);
}
