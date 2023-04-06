package np.com.esewa.learn.sampleapplication.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import np.com.esewa.learn.sampleapplication.inventory.model.ProductStatus;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequestDto implements Serializable {
 private String name;
 private String code;
 private Long quantity;
}
