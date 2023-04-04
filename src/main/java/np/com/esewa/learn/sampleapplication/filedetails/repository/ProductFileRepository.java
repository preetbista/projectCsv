package np.com.esewa.learn.sampleapplication.filedetails.repository;

import np.com.esewa.learn.sampleapplication.filedetails.model.FileStatus;
import np.com.esewa.learn.sampleapplication.filedetails.model.ProductFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFileRepository extends JpaRepository<ProductFile, Long> {

    List<ProductFile> findAllByStatus(FileStatus status);
}
