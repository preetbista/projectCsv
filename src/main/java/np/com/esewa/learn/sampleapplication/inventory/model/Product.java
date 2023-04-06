package np.com.esewa.learn.sampleapplication.inventory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Code", nullable = false, unique = true)
    private String code;
    @Column(name = "Quantity")
    private Long quantity;
    @Column(name = "Price")
    private Long price;
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private ProductStatus status;
}
