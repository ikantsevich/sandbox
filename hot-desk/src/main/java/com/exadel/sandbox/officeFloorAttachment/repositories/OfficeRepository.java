package com.exadel.sandbox.officeFloorAttachment.repositories;

import com.exadel.sandbox.officeFloorAttachment.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
    Office getOfficeByAddressId(Long id);
}