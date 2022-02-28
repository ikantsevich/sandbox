package com.exadel.sandbox.vacation.repository;

import com.exadel.sandbox.vacation.entities.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VacationRepository extends JpaRepository<Vacation, Long> {
    Optional<Vacation> findByEmployeeId(Long employee_id);
}
