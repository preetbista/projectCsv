package np.com.esewa.learn.sampleapplication.inventory.service.impl;

import np.com.esewa.learn.sampleapplication.inventory.dto.ProductDeleteDto;
import np.com.esewa.learn.sampleapplication.inventory.dto.ProductResponseDto;
import np.com.esewa.learn.sampleapplication.inventory.model.Product;
import np.com.esewa.learn.sampleapplication.inventory.model.ProductStatus;
import np.com.esewa.learn.sampleapplication.inventory.repository.ProductRepository;
import np.com.esewa.learn.sampleapplication.inventory.service.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productServiceImpl;
    @Mock
    private ProductRepository testProductRepository;

    @Test
    @DisplayName("find by id true condition")
    public void findById_findProductUsingId_True() {
        //Arrange
        Product newProduct = new Product();
        newProduct.setId(1);
        newProduct.setName("Laptop");

        ProductResponseDto products = new ProductResponseDto();

        products.setId(1);
        products.setName("Laptop");

        when(testProductRepository.findById(products.getId())).thenReturn(Optional.of(newProduct));

        //Act
        ProductResponseDto find = productServiceImpl.findById(products.getId());

        //Assert
        assertNotNull(find);
        assertEquals("Laptop", find.getName());
    }

    @Test
    @DisplayName("fails when id not provided")
    public void findById_FailsWhenIdNotGiven_True() {
        //Arrange
        Product newProduct = new Product();
        newProduct.setName("Laptop");

        ProductResponseDto products = new ProductResponseDto();

        products.setName("Laptop");
        when(testProductRepository.findById(products.getId())).thenReturn(Optional.of(newProduct));

        //Act
        ProductResponseDto find = productServiceImpl.findById(products.getId());

        //Assert
        assertNotNull(find);
        assertEquals(0, find.getId());
    }

    @Test
    @DisplayName("delete product by id true condition")
    public void deleteProduct_DeleteProductById_True() {
        // Arrange
        Product productToBeDeleted = new Product();
        productToBeDeleted.setId(1);
        productToBeDeleted.setStatus(ProductStatus.ACTIVE); // Assuming initial status is ACTIVE
        when(testProductRepository.findById(1)).thenReturn(Optional.of(productToBeDeleted));

        // Act
        ProductDeleteDto resultOfDeleteDto = productServiceImpl.deleteProduct(1);

        // Assert
        verify(testProductRepository, times(1)).findById(1);
        verify(testProductRepository, times(1)).save(productToBeDeleted);
        assertEquals("Product deleted successfully", resultOfDeleteDto.getMessage());
        assertEquals(ProductStatus.DELETED, productToBeDeleted.getStatus());
    }

    @Test
    @DisplayName("read data from csv file true condition")
    public void scanDataFromFile_ReadingDataFromFilePath_True(){
        //Arrange
        List<Product> listOfProduct = new ArrayList<>();

        //Act
        listOfProduct = productServiceImpl.scanDataFromFile("C:\\Users\\Lenovo\\Desktop\\projectCSV\\CSV-Reading-MultiDB-Spring-Boot-RealTime-main\\src\\main\\resources\\wears.csv");

        //Assert
        assertEquals("Shoes", listOfProduct.get(0).getName());
    }

    @Test
    @DisplayName("add product data")
    public void addProduct_AddProductsData_True(){
        //Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("Computer");
        product.setCode("C3");
        product.setPrice(12000L);
        product.setQuantity(12L);
        product.setStatus(ProductStatus.ACTIVE);

    }
}

