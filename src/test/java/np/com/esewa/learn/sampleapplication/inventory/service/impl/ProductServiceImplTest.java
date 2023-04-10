package np.com.esewa.learn.sampleapplication.inventory.service.impl;

import np.com.esewa.learn.sampleapplication.inventory.dto.ProductResponseDto;
import np.com.esewa.learn.sampleapplication.inventory.model.Product;
import np.com.esewa.learn.sampleapplication.inventory.repository.ProductRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository testProductRepository;

    @Test
    public void findById_findProductUsingId_True() {
        Product testProduct = new Product();
        ProductResponseDto testProductResponse = new ProductResponseDto();
        when(testProductRepository.findById(1)).thenReturn(Optional.of(testProduct));
        assertEquals("Mobile", testProductResponse.getName());
    }

    @Test
    @Disabled("Until find by Id is solved")
    void readProductDataFromFile() {
    }

    @Test
    @Disabled("Until find by Id is solved")
    void addProduct() {
    }
}