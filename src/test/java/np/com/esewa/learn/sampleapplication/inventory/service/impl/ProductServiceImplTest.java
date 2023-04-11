package np.com.esewa.learn.sampleapplication.inventory.service.impl;

import np.com.esewa.learn.sampleapplication.inventory.dto.ProductResponseDto;
import np.com.esewa.learn.sampleapplication.inventory.model.Product;
import np.com.esewa.learn.sampleapplication.inventory.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productServiceImpl;
    @Mock
    private ProductRepository testProductRepository;
    Product newProduct = new Product();

    @Test
    public void findById_findProductUsingId_True() {
        //Arrange
        ProductResponseDto product = new ProductResponseDto();

        product.setId(1);
        product.setName("Laptop");
        product.setCode("L21");
        product.setQuantity(10L);

        when(testProductRepository.findById(product.getId())).thenReturn(Optional.of(newProduct));

        //Act
        ProductResponseDto find = productServiceImpl.findById(product.getId());

        //Assert
        assertNotNull(find);
        assertEquals(1, find.getId());
    }


}