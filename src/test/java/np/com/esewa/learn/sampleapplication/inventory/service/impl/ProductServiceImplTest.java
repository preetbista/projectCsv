package np.com.esewa.learn.sampleapplication.inventory.service.impl;

import np.com.esewa.learn.sampleapplication.inventory.dto.ProductResponseDto;
import np.com.esewa.learn.sampleapplication.inventory.model.Product;
import np.com.esewa.learn.sampleapplication.inventory.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
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


    @Test
    @DisplayName("find by id true condition")
    public void findById_findProductUsingId_True() {
        Product newProduct = new Product();
        newProduct.setId(1);
        newProduct.setName("Laptop");
        newProduct.setCode("L21");
        newProduct.setQuantity(10L);
        //Arrange
        ProductResponseDto products = new ProductResponseDto();

        products.setId(1);
        products.setName("Laptop");
        products.setCode("L21");
        products.setQuantity(10L);

        when(testProductRepository.findById(products.getId())).thenReturn(Optional.of(newProduct));

        //Act
        ProductResponseDto find = productServiceImpl.findById(products.getId());

        //Assert
        assertNotNull(find);
        assertEquals("Laptop", find.getName());
    }


}