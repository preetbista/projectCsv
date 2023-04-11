package np.com.esewa.learn.sampleapplication.inventory.repository;

import np.com.esewa.learn.sampleapplication.inventory.model.Product;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductByCode(String productCode);
}
