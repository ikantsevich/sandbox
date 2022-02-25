package com.exadel.sandbox.notification.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.notification.dto.NotificationCreateDto;
import com.exadel.sandbox.notification.dto.NotificationResponseDto;
import com.exadel.sandbox.notification.entity.Notification;
import com.exadel.sandbox.notification.repository.NotificationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotificationService extends BaseCrudService<Notification, NotificationResponseDto, Notification, NotificationCreateDto, NotificationRepository> {
    private final MailSender mailSender;

    public NotificationService(ModelMapper mapper, NotificationRepository repository, MailSender mailSender) {
        super(mapper, repository);
        this.mailSender = mailSender;
    }

    public ResponseEntity<EmployeeResponseDto> findByEmployeeId(Long id) {
        List<Notification> notifications = repository.findNotificationsByEmployeeId(id);
        return ResponseEntity.ok(mapper.map(notifications, new TypeToken<List<EmployeeResponseDto>>() {}.getType()));
    }

    public NotificationResponseDto send(NotificationCreateDto notificationCreateDto) {
        Notification notification = mapper.map(notificationCreateDto, Notification.class);
        repository.save(notification);
        return mapper.map(sendMail(notification), NotificationResponseDto.class);
    }

    private Notification sendMail(Notification notification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hotdesktcompany@gmail.com");
        message.setSubject(notification.getTitle());
        message.setTo(notification.getEmployee().getEmail());
        message.setText(notification.getMessage());
        mailSender.send(message);
        return notification;
    }
}
