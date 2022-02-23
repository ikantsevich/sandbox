package com.exadel.sandbox.notification.service.impl;

import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.notification.dto.NotificationCreateDto;
import com.exadel.sandbox.notification.dto.NotificationResponseDto;
import com.exadel.sandbox.notification.entity.Notification;
import com.exadel.sandbox.notification.repository.NotificationRepository;
import com.exadel.sandbox.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.math.raw.Mod;
import org.modelmapper.ModelMapper;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final ModelMapper mapper;
    private final JavaMailSender mailSender;

    @Override
    public List<NotificationResponseDto> getAll() {
        return notificationRepository
                .findAll()
                .stream()
                .map(notification -> mapper.map(notification, NotificationResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public NotificationResponseDto findById(Long id){
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Notification with id: " + id + " not found"));

        return mapper.map(notification, NotificationResponseDto.class);
    }

    @Override
    public List<NotificationResponseDto> findByEmployeeId(Long id) {
        return notificationRepository
                .findNotificationsByEmployeeId(id)
                .stream()
                .map(notification -> mapper.map(notification, NotificationResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public NotificationResponseDto send(NotificationCreateDto notificationCreateDto) {
        Notification notification = mapper.map(notificationCreateDto, Notification.class);
        notificationRepository.save(notification);
        return mapper.map(sendMail(notification), NotificationResponseDto.class);
    }

    private Notification sendMail(Notification notification){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(notification.getTitle());
        message.setTo(notification.getEmployee().getEmail());
        message.setText(notification.getMessage());
        mailSender.send(message);
        return notification;
    }
}
