package np.com.esewa.learn.sampleapplication.inventory.dto;

import lombok.Getter;
import lombok.Setter;
import np.com.esewa.learn.sampleapplication.inventory.model.ProductStatus;

import java.io.Serializable;

@Getter
@Setter
public class ProductResponseDto implements Serializable {

    private String name;
    private String code;
    private Long quantity;

}
