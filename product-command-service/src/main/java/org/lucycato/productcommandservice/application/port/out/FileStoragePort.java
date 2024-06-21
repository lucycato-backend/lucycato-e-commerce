package org.lucycato.productcommandservice.application.port.out;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStoragePort {

    String saveImageFile(MultipartFile imageFile);

    List<String> saveImageFiles(List<MultipartFile> imageFiles);

    String saveVideoFile(MultipartFile videoFile);

    String savePdfFile(MultipartFile pdfFile);
}
