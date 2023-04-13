package np.com.esewa.learn.sampleapplication.inventory.service;

import np.com.esewa.learn.sampleapplication.filedetails.dto.CountDto;
import np.com.esewa.learn.sampleapplication.inventory.annotation.EncryptName;
import np.com.esewa.learn.sampleapplication.inventory.dto.ProductDeleteDto;
import np.com.esewa.learn.sampleapplication.inventory.dto.ProductResponseDto;
import np.com.esewa.learn.sampleapplication.inventory.model.Product;
import np.com.esewa.learn.sampleapplication.inventory.model.ProductStatus;
import np.com.esewa.learn.sampleapplication.inventory.repository.ProductRepository;
import np.com.esewa.learn.sampleapplication.inventory.service.ProductService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto findById(int id) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        Product retrievedProduct = productRepository.findById(id).orElseThrow(null);
        if (retrievedProduct != null) {
            productResponseDto.setId(retrievedProduct.getId());
            productResponseDto.setName(retrievedProduct.getName());
            productResponseDto.setQuantity(retrievedProduct.getQuantity());
            productResponseDto.setCode(retrievedProduct.getCode());
        }
        return productResponseDto;
    }

    @Override
    public List<Product> scanDataFromFile(String filePath) {
        List<Product> productList = new ArrayList<>();
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            while ((line = bufferedReader.readLine()) != null) {
                String[] row = line.split(",");
                Product product = new Product();
                // reading product details form file
                product.setName(row[0]);
                product.setCode(row[1]);
                product.setQuantity(Long.parseLong(row[2]));
                product.setPrice(Long.parseLong(row[3]));
                productList.add(product); // converted csv data to list of product
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    @EncryptName
    public CountDto addProduct(List<Product> productList) {
        CountDto countDto = new CountDto();
        int failCount = 0;
        int successCount = 0;
        for (Product product : productList) {
            if (!productList.isEmpty()) {
                Product existingProduct = productRepository.findProductByCode(product.getCode());
                if (!(existingProduct == null)
                        && (existingProduct.getStatus() == ProductStatus.ACTIVE
                        && existingProduct.getCode().equals(product.getCode()))) {
                    failCount++;
                    continue;
                }
            }
            product.setStatus(ProductStatus.ACTIVE);
            productRepository.save(product);
            successCount++;
        }
        countDto.setFAIL_COUNT(failCount);
        countDto.setSUCCESS_COUNT(successCount);

        return countDto;
    }

    @Override
    public ProductDeleteDto deleteProduct(int id) {
        Product productToBeDeleted = productRepository.findById(id).orElseThrow(null);
        productToBeDeleted.setStatus(ProductStatus.DELETED);
        productRepository.save(productToBeDeleted);
        ProductDeleteDto productDeleteDto = new ProductDeleteDto();
        productDeleteDto.setMessage("Product deleted successfully");
        return productDeleteDto;
    }


}
