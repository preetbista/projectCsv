package np.com.esewa.learn.sampleapplication.notification.service;

import np.com.esewa.learn.sampleapplication.notification.dto.NotificationDto;
import np.com.esewa.learn.sampleapplication.notification.dto.NotificationRequestDto;
import np.com.esewa.learn.sampleapplication.notification.dto.NotificationResponseDto;

public interface NotificationService{
    NotificationResponseDto findById(int id);
    void saveNotification(NotificationRequestDto notificationRequestDto);
    void sendNotification(NotificationDto notificationDto);
}
