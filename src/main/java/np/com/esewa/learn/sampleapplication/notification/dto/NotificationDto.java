package np.com.esewa.learn.sampleapplication.notification.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class NotificationDto implements Serializable {
    private String message;
}
