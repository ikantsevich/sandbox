package com.exadel.sandbox.repositories;

import com.exadel.sandbox.entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    Attachment getAllByMessageId(Long id);
}