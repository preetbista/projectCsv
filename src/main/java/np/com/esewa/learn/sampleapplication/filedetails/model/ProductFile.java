package np.com.esewa.learn.sampleapplication.filedetails.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "file_details")
public class ProductFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String filePath;
    @Enumerated(EnumType.STRING)
    private FileStatus status;
    private Long SUCCESS_COUNT;
    private Long FAIL_COUNT;
}
