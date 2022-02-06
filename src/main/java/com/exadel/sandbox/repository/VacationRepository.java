package com.exadel.sandbox.repository;

import com.exadel.sandbox.entities.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRepository extends JpaRepository<Vacation, Integer> {

}
