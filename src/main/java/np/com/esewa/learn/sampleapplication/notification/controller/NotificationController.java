package np.com.esewa.learn.sampleapplication.notification.controller;

import lombok.extern.slf4j.Slf4j;
import np.com.esewa.learn.sampleapplication.notification.dto.NotificationRequestDto;
import np.com.esewa.learn.sampleapplication.notification.dto.NotificationResponseDto;
import np.com.esewa.learn.sampleapplication.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/notification")
public class NotificationController {

    public final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    void addNotification(@RequestBody NotificationRequestDto notificationRequestDto) {
        notificationService.saveNotification(notificationRequestDto);
    }

    @GetMapping("/{id}")
    public NotificationResponseDto addNotification(@PathVariable int id){
        return notificationService.findById(id);
    }




}
