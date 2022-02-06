package com.exadel.sandbox.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TgInfoDto {
    Integer infoId;
    String chatId;
    String chatState;
    String username;
    Date infoCreated;
    Date infoModified;
}
