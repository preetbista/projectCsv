package np.com.esewa.learn.sampleapplication.inventory.repository;

import np.com.esewa.learn.sampleapplication.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductByCode(String productCode);
    Product deleteProduct(int id);
}
