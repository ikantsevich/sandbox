package com.exadel.hotdesk.vacation.repository;

import com.exadel.hotdesk.vacation.entities.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRepository extends JpaRepository<Vacation, Long> {

}
