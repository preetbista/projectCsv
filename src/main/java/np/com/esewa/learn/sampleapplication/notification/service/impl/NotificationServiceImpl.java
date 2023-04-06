package np.com.esewa.learn.sampleapplication.notification.service.impl;

import np.com.esewa.learn.sampleapplication.notification.dto.NotificationDto;
import np.com.esewa.learn.sampleapplication.notification.dto.NotificationRequestDto;
import np.com.esewa.learn.sampleapplication.notification.dto.NotificationResponseDto;
import np.com.esewa.learn.sampleapplication.notification.model.Notification;
import np.com.esewa.learn.sampleapplication.notification.repository.NotificationRepository;
import np.com.esewa.learn.sampleapplication.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public NotificationResponseDto findById(int id){
        NotificationResponseDto notificationResponseDto = new NotificationResponseDto();

        Notification notification = notificationRepository.findById(id).orElseThrow(null);
        notificationResponseDto.setUsername(notification.getUsername());
        notificationResponseDto.setUrl(notification.getUrl());
        notificationResponseDto.setPassword(notification.getPassword());

        return notificationResponseDto;
    }

    @Override
    public void saveNotification(NotificationRequestDto notificationRequestDto){
        Notification notification = new Notification();
        notification.setUsername(notificationRequestDto.getUsername());
        notification.setUrl(notificationRequestDto.getUrl());
        notification.setPassword(notificationRequestDto.getPassword());
        notificationRepository.save(notification);
    }
    @Override
    public void sendNotification(NotificationDto notificationDto){
        List<Notification> userNotification = notificationRepository.findAll();
        for (Notification notification : userNotification) {
            String url = notification.getUrl();

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<NotificationDto> request = new HttpEntity<>(notificationDto);
            restTemplate.postForEntity(url, request, String.class);
        }

    }


}
