package np.com.esewa.learn.sampleapplication.notification.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "third_party")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private final String type ="NOTIFY";
    private String url;
    private String username;
    private String password;
    private String is_encrypted;

}
