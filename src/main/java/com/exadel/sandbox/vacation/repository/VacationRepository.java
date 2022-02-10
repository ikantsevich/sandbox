package com.exadel.sandbox.vacation.repository;

import com.exadel.sandbox.vacation.entities.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRepository extends JpaRepository<Vacation, Long> {

}
