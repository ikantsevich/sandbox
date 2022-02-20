package com.exadel.sandbox.attachment.repository;

import com.exadel.sandbox.attachment.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    Attachment findAttachmentByFloorId(Long id);
}
