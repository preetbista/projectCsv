package np.com.esewa.learn.sampleapplication.notification.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class NotificationRequestDto implements Serializable {
        private String url;
        private String username;

        private String password;
}
