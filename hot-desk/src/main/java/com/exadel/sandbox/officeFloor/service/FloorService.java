package com.exadel.sandbox.officeFloor.service;

import com.exadel.sandbox.officeFloor.dto.floorDto.FloorCreateDto;
import com.exadel.sandbox.officeFloor.dto.floorDto.FloorResponseDto;
import com.exadel.sandbox.officeFloor.dto.floorDto.FloorUpdateDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface FloorService {
    List<FloorResponseDto> getFloors();

    FloorResponseDto getById(Long id);

    FloorResponseDto create(FloorCreateDto floorCreateDto);

    void deleteById(Long id);

    FloorResponseDto update(Long id, FloorUpdateDto floorUpdateDto);

    List<FloorResponseDto> getFloorsByOfId(Long id);

    void setMap(Long id, MultipartHttpServletRequest request);

    void getMap(Long id, HttpServletResponse response);
}
