package np.com.esewa.learn.sampleapplication.filedetails.service.impl;

import np.com.esewa.learn.sampleapplication.filedetails.dto.CountDto;
import np.com.esewa.learn.sampleapplication.filedetails.dto.FileDetailsRequestDto;
import np.com.esewa.learn.sampleapplication.filedetails.dto.FileDetailsResponseDto;
import np.com.esewa.learn.sampleapplication.filedetails.model.FileStatus;
import np.com.esewa.learn.sampleapplication.filedetails.model.ProductFile;
import np.com.esewa.learn.sampleapplication.filedetails.repository.ProductFileRepository;
import np.com.esewa.learn.sampleapplication.filedetails.service.ProductFileService;
import np.com.esewa.learn.sampleapplication.inventory.model.Product;
import np.com.esewa.learn.sampleapplication.inventory.service.ProductService;
import np.com.esewa.learn.sampleapplication.notification.dto.NotificationDto;
import np.com.esewa.learn.sampleapplication.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductFileServiceImpl implements ProductFileService {
    @Autowired
    private ProductFileRepository productFileRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private NotificationService notificationService;

    @Override
    public void saveFile(FileDetailsRequestDto fileDetailsRequestDto) {
        ProductFile productFile = new ProductFile();
        productFile.setFilePath(fileDetailsRequestDto.getFilePath());
        productFile.setStatus(FileStatus.PENDING);
        productFile.setFAIL_COUNT(0L);
        productFile.setSUCCESS_COUNT(0L);
        productFileRepository.save(productFile);
    }

    @Override
    public FileDetailsResponseDto getFileById(int filePathId) {
        FileDetailsResponseDto fileDetailsResponseDto = new FileDetailsResponseDto();

        ProductFile productFile = productFileRepository.findById(filePathId).orElseThrow(null);
        fileDetailsResponseDto.setFilePath(productFile.getFilePath());
        fileDetailsResponseDto.setStatus(productFile.getStatus());

        return fileDetailsResponseDto;
    }

    @Scheduled(cron = "${spring.fixedRateInMs}")
    void processFile() {
        List<ProductFile> productFileList = productFileRepository.findAllByStatus(FileStatus.PENDING);
        for (ProductFile productFile : productFileList) {
            productFile.setStatus(FileStatus.PROCESSING);
            productFileRepository.save(productFile);
            List<Product> productList = productService.readProductDataFromFile(productFile.getFilePath());
            CountDto countDto = productService.addProduct(productList);
            productFile.setFAIL_COUNT(countDto.getFAIL_COUNT());
            productFile.setSUCCESS_COUNT(countDto.getSUCCESS_COUNT());
            productFile.setStatus(FileStatus.COMPLETED);
            productFileRepository.save(productFile);

            NotificationDto notificationDto = new NotificationDto();
            String message = "Out of" + (productFile.getFAIL_COUNT() + productFile.getSUCCESS_COUNT()) + "products"
                    + productFile.getFAIL_COUNT() + "failed and " + productFile.getSUCCESS_COUNT() + "added successfully";

            notificationDto.setMessage(message);
            notificationService.sendNotification(notificationDto);
        }
    }
}
