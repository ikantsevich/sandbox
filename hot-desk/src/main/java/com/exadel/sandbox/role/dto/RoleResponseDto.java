package com.exadel.sandbox.role.dto;

import com.exadel.sandbox.permission.dto.PermissionResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id"})
public class RoleResponseDto extends RoleBaseDto {
    private Long id;
    @JsonProperty("permissions")
    private List<PermissionResponseDto> perResponseDtoList;
    private LocalDateTime created;
    private LocalDateTime modified;
}