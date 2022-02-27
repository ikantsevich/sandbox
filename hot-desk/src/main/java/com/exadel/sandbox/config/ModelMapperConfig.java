package com.exadel.sandbox.config;

import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.entity.TgInfo;
import com.exadel.sandbox.employee.repository.EmployeeRepository;
import com.exadel.sandbox.equipment.entity.Equipment;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.notification.entity.Notification;
import com.exadel.sandbox.officeFloor.entities.Floor;
import com.exadel.sandbox.officeFloor.entities.Office;
import com.exadel.sandbox.officeFloor.repositories.FloorRepository;
import com.exadel.sandbox.officeFloor.repositories.OfficeRepository;
import com.exadel.sandbox.parking_spot.entity.ParkingSpot;
import com.exadel.sandbox.role.entity.Role;
import com.exadel.sandbox.role.repository.RoleRepository;
import com.exadel.sandbox.seat.entity.Seat;
import com.exadel.sandbox.seat.repository.SeatRepository;
import com.exadel.sandbox.vacation.entities.Vacation;
import dtos.employee.dto.employeeDto.EmployeeCreateDto;
import dtos.employee.dto.employeeDto.EmployeeResponseDto;
import dtos.equipment.dto.EquipmentUpdateDto;
import dtos.notification.dto.NotificationCreateDto;
import dtos.notification.dto.NotificationResponseDto;
import dtos.officeFloor.dto.floorDto.FloorCreateDto;
import dtos.officeFloor.dto.floorDto.FloorResponseDto;
import dtos.officeFloor.dto.officeDto.OfficeResponseDto;
import dtos.parkingSpot.dto.ParkingSpotCreateDto;
import dtos.parkingSpot.dto.ParkingSpotResponseDto;
import dtos.role.dto.RoleResponseDto;
import dtos.seat.dto.SeatCreateDto;
import dtos.seat.dto.SeatResponseDto;
import dtos.vacation.dto.VacationCreateDto;
import dtos.vacation.dto.VacationResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final SeatRepository seatRepository;
    private final FloorRepository floorRepository;
    private final OfficeRepository officeRepository;

    @Bean
    ModelMapper mapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

//        EMPLOYEE -> EMPLOYEE_RESPONSE_DTO
        mapper.typeMap(Employee.class, EmployeeResponseDto.class).addMappings(m -> {
            m.map(employee -> employee.getTgInfo().getId(), EmployeeResponseDto::setTgInfoId);
            m.map(Employee::getRoles, EmployeeResponseDto::setRoles);
            m.map(Employee::getVacations, EmployeeResponseDto::setVacations);
        });

//        EMPLOYEE_CREATE_DTO -> EMPLOYEE
        Converter<EmployeeCreateDto, Employee> employeeCreateDtoEmployeeConverter = new Converter<>() {
            private final ModelMapper m = new ModelMapper();

            @Override
            public Employee convert(MappingContext<EmployeeCreateDto, Employee> context) {
                m.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                Employee employee = m.map(context.getSource(), Employee.class);

                if (context.getSource().getRoles() != null) {
                    List<Role> roles = context.getSource().getRoles().stream().map(role -> roleRepository.findRoleByName(role).orElseThrow(
                            () -> new EntityNotFoundException("Role with name: " + role + " not found")
                    )).collect(Collectors.toList());

                    employee.setRoles(roles);
                }
                if (context.getSource().getRoles() != null) {
                    employee.setTgInfo(m.map(context.getSource().getTgInfoCreateDto(), TgInfo.class));
                }

                return employee;
            }
        };

//        EQUIPMENT_CREATE_DTO -> EQUIPMENT
        Converter<EquipmentUpdateDto, Equipment> equipmentUpdateDtoEquipmentConverter = new Converter<>() {
            private final ModelMapper m = new ModelMapper();

            @Override
            public Equipment convert(MappingContext<EquipmentUpdateDto, Equipment> context) {
                m.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

                Equipment equipment = m.map(context.getSource(), Equipment.class);

                if (context.getSource().getSeatId() != null) {
                    Seat seat = seatRepository.findById(context.getSource().getSeatId()).orElseThrow(
                            () -> new EntityNotFoundException("Seat with id" + context.getSource().getSeatId() + " not found")
                    );
                    equipment.setSeat(seat);
                }

                return equipment;
            }
        };

//        SEAT -> SEAT_RESPONSE_DTO
        mapper.typeMap(Seat.class, SeatResponseDto.class).addMappings(m -> {
            m.map(seat -> seat.getFloor().getId(), SeatResponseDto::setFloorId);
            m.map(Seat::getEquipments, SeatResponseDto::setEquipmentResponseDtos);
        });

//        SEAT_CREATE_DTO -> SEAT
        Converter<SeatCreateDto, Seat> seatCreateDtoSeatConverter = new Converter<>() {
            private final ModelMapper m = new ModelMapper();

            @Override
            public Seat convert(MappingContext<SeatCreateDto, Seat> context) {
                m.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

                Seat seat = m.map(context.getSource(), Seat.class);

                if (context.getSource().getFloorId() != null) {
                    Floor floor = floorRepository.findById(context.getSource().getFloorId()).orElseThrow(
                            () -> new EntityNotFoundException("Floor not found")
                    );

                    seat.setFloor(floor);
                }
                return seat;
            }
        };

//        VACATION -> VACATION_RESPONSE_DTO
        mapper.typeMap(Vacation.class, VacationResponseDto.class).addMappings(m -> m.map(vacation -> vacation.getEmployee().getId(), VacationResponseDto::setEmployeeId));

//        VACATION_CREATE_DTO -> VACATION
        Converter<VacationCreateDto, Vacation> vacationCreateDtoVacationConverter = new Converter<>() {
            private final ModelMapper m = new ModelMapper();

            @Override
            public Vacation convert(MappingContext<VacationCreateDto, Vacation> context) {
                m.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

                Vacation vacation = m.map(context.getSource(), Vacation.class);

                if (context.getSource().getEmployeeId() != null) {
                    Employee employee = employeeRepository.findById(context.getSource().getEmployeeId()).orElseThrow(
                            () -> new EntityNotFoundException("Employee not found")
                    );
                    vacation.setEmployee(employee);
                }
                return vacation;
            }
        };

//        ROLE -> ROLE_RESPONSE_DTO
        mapper.typeMap(Role.class, RoleResponseDto.class).addMappings(m -> m.map(Role::getPermissions, RoleResponseDto::setPerResponseDtoList));

//        NOTIFICATION -> NOTIFICATION_RESPONSE_DTO
        mapper.typeMap(Notification.class, NotificationResponseDto.class).addMappings(m -> m.map(notification -> notification.getEmployee().getId(), NotificationResponseDto::setEmployeeId));

//        NOTIFICATION_CREATE_DTO -> NOTIFICATION
        Converter<NotificationCreateDto, Notification> notificationCreateDtoNotificationConverter = new Converter<>() {
            private final ModelMapper m = new ModelMapper();

            @Override
            public Notification convert(MappingContext<NotificationCreateDto, Notification> context) {
                m.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

                Notification notification = m.map(context.getSource(), Notification.class);

                if (context.getSource().getEmployeeId() != null) {
                    Employee employee = employeeRepository.findById(context.getSource().getEmployeeId()).orElseThrow(
                            () -> new EntityNotFoundException("Employee not found")
                    );
                    notification.setEmployee(employee);
                }
                return notification;
            }
        };

//        OFFICE -> OFFICE_RESPONSE_DTO
        mapper.typeMap(Office.class, OfficeResponseDto.class).addMappings(m -> m.map(Office::getAddress, OfficeResponseDto::setAddress));

//        FLOOR -> FLOOR_RESPONSE_DTO
        mapper.typeMap(Floor.class, FloorResponseDto.class).addMappings(m -> {
            m.map(floor -> floor.getOffice().getId(), FloorResponseDto::setOfficeId);
            m.map(floor -> floor.getAttachment().getId(), FloorResponseDto::setAttachmentId);
        });

//        FLOOR_CREATE_DTO -> FLOOR
        Converter<FloorCreateDto, Floor> floorCreateDtoFloorConverter = new Converter<>() {
            private final ModelMapper m = new ModelMapper();

            @Override
            public Floor convert(MappingContext<FloorCreateDto, Floor> context) {
                m.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

                Floor floor = m.map(context.getSource(), Floor.class);

                if (context.getSource().getOfficeId() != null) {
                    Office office = officeRepository.findById(context.getSource().getOfficeId()).orElseThrow(
                            () -> new EntityNotFoundException("Office not found")
                    );
                    floor.setOffice(office);
                }
                return floor;
            }
        };

//        PARKING_SPOT -> PARKING_SPOT_RESPONSE_DTO
        mapper.typeMap(ParkingSpot.class, ParkingSpotResponseDto.class).addMappings(m -> m.map(parking -> parking.getOffice().getId(), ParkingSpotResponseDto::setOfficeId));

        Converter<ParkingSpotCreateDto, ParkingSpot> parkingSpotCreateDtoParkingSpotConverter = new Converter<>() {
            private final ModelMapper m = new ModelMapper();

            @Override
            public ParkingSpot convert(MappingContext<ParkingSpotCreateDto, ParkingSpot> context) {
                m.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

                ParkingSpot parkingSpot = m.map(context.getSource(), ParkingSpot.class);

                if (context.getSource().getOfficeId() != null) {
                    Office office = officeRepository.findById(context.getSource().getOfficeId()).orElseThrow(
                            () -> new EntityNotFoundException("Office not found")
                    );
                    parkingSpot.setOffice(office);
                }
                return parkingSpot;
            }
        };

        mapper.addConverter(equipmentUpdateDtoEquipmentConverter);
        mapper.addConverter(employeeCreateDtoEmployeeConverter);
        mapper.addConverter(seatCreateDtoSeatConverter);
        mapper.addConverter(vacationCreateDtoVacationConverter);
        mapper.addConverter(notificationCreateDtoNotificationConverter);
        mapper.addConverter(floorCreateDtoFloorConverter);
        mapper.addConverter(parkingSpotCreateDtoParkingSpotConverter);
//
        return mapper;
    }
}
