package np.com.esewa.learn.sampleapplication.inventory.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductDeleteDto implements Serializable {
    private int id;
    private String message;
}
