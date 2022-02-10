package com.exadel.sandbox.notification.service.impl;

import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.notification.dto.NotificationBaseDto;
import com.exadel.sandbox.notification.dto.NotificationResponseDto;
import com.exadel.sandbox.notification.entity.Notification;
import com.exadel.sandbox.notification.repository.NotificationRepository;
import com.exadel.sandbox.notification.service.NotificationService;
import com.exadel.sandbox.seat.dto.SeatResponseDto;
import com.exadel.sandbox.seat.entity.Seat;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
@Transactional
public class NotificationServiceImpl implements NotificationService{

    private final NotificationRepository notificationRepository;
    private final ModelMapper mapper;

    @Override
    public List<NotificationResponseDto> getNotifications() {
        List<Notification> notificationList = notificationRepository.findAll();

        List<NotificationResponseDto> notificationResponseDtoList = new ArrayList<>();

        for (Notification notification :
                notificationList) {
            notificationResponseDtoList.add(fullMap(notification));
        }

        return notificationResponseDtoList;
    }

    @Override
    public NotificationResponseDto getNotificationById(Long id) {

        Optional<Notification> byId = notificationRepository.findById(id);

        Notification notification = byId.orElseThrow( ()-> new EntityNotFoundException("Notif with id: " + id + " not found"));


        return fullMap(notification);
    }

    @Override
    public NotificationResponseDto create(NotificationBaseDto notificationBaseDto) {
        Notification notification = mapper.map(notificationBaseDto , Notification.class);

        return fullMap(notificationRepository.save(notification));
    }

    @Override
    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public NotificationResponseDto update(Long id, NotificationBaseDto notificationBaseDto) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Notif with id: " + id + " not found"));

        mapper.map(notificationBaseDto, notification);

        return fullMap(notificationRepository.save(notification));
    }



    private NotificationResponseDto fullMap(Notification notification) {
        NotificationResponseDto notificationResponseDto = mapper.map(notification, NotificationResponseDto.class);


        return null;
    }

}
