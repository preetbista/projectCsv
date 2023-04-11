package np.com.esewa.learn.sampleapplication.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequestDto implements Serializable {
 private int id;
 private String name;
 private String code;
 private Long quantity;
}
