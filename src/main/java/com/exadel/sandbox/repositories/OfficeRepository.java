package com.exadel.sandbox.repositories;

import com.exadel.sandbox.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
    Office getOfficeByAddressId(Long id);
}