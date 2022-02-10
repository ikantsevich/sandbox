package com.exadel.sandbox.officeFloorAttachment.controllers.repositories;

import com.exadel.sandbox.officeFloorAttachment.controllers.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface OfficeRepository extends JpaRepository<Office, Long> {
    Office getOfficeByAddressId(Long id);
}