package np.com.esewa.learn.sampleapplication.inventory.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductResponseDto implements Serializable {
    private int id;
    private String name;
    private String code;
    private Long quantity;

}
