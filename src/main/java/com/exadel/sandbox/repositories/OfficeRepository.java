package com.exadel.sandbox.repositories;

import com.exadel.sandbox.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfficeRepository extends JpaRepository<Office, Long> {
    Office getOfficeByAddressId(Long id);
}