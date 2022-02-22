package com.exadel.sandbox.officeFloor.repositories;

import com.exadel.sandbox.officeFloor.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
}