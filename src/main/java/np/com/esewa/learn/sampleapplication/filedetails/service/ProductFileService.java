package np.com.esewa.learn.sampleapplication.filedetails.service;

import np.com.esewa.learn.sampleapplication.filedetails.dto.FileDetailsRequestDto;
import np.com.esewa.learn.sampleapplication.filedetails.dto.FileDetailsResponseDto;

public interface ProductFileService {
    void saveFile(FileDetailsRequestDto fileDetailsRequestDto);
    FileDetailsResponseDto getFileById(int filePathId);

}
