package com.exadel.sandbox.officeFloorAttachment.controllers.repositories;

import com.exadel.sandbox.officeFloorAttachment.controllers.entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    Attachment getAllByMessageId(Long id);
}
