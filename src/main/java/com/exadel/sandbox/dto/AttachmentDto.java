package com.exadel.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDto {
    private Long id;
    private String  chatId;
    private Long messageId;
    private String atName;
    private Long atSize;
    private Date atCreated;
    private Date atModified;
}
