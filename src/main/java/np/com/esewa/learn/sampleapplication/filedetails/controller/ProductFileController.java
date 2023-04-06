package np.com.esewa.learn.sampleapplication.filedetails.controller;

import lombok.extern.slf4j.Slf4j;
import np.com.esewa.learn.sampleapplication.filedetails.dto.FileDetailsRequestDto;
import np.com.esewa.learn.sampleapplication.filedetails.dto.FileDetailsResponseDto;
import np.com.esewa.learn.sampleapplication.filedetails.service.ProductFileService;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/files")
public class ProductFileController {
    public final ProductFileService productFileService;

    public ProductFileController(ProductFileService productFileService) {
        this.productFileService = productFileService;
    }

    @PostMapping
    void addFile(@RequestBody FileDetailsRequestDto fileDetailsRequestDto) {
        productFileService.saveFile(fileDetailsRequestDto);
    }

    @GetMapping("/{filePathId}")
    public FileDetailsResponseDto addFile(@PathVariable int filePathId) {
        return productFileService.getFileById(filePathId);
    }
}
