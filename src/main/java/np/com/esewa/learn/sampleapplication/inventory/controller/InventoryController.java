package np.com.esewa.learn.sampleapplication.inventory.controller;

import lombok.extern.slf4j.Slf4j;
import np.com.esewa.learn.sampleapplication.inventory.dto.ProductDeleteDto;
import np.com.esewa.learn.sampleapplication.inventory.dto.ProductResponseDto;
import np.com.esewa.learn.sampleapplication.inventory.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/products")
public class InventoryController {
    @Autowired
    private ProductService productService;
    public static final Logger logger = LoggerFactory.getLogger(InventoryController.class);
    @GetMapping("/{id}")
    /*@Cacheable(value = "productName", key = "#id")*/
    public ProductResponseDto findById(@PathVariable int id) {
        logger.info("Data from findById");
        return productService.findById(id);
    }

    @CacheEvict(value = "productName", key = "#id")
    @DeleteMapping("/{id}")
    public ProductDeleteDto deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}
