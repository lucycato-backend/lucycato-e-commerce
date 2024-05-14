package org.lucycato.productservice.application.port.out;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.List;

public interface FileStorePort {
    Mono<String> saveImageFile(MultipartFile imageFile);

    Mono<List<String>> saveImageFiles(List<MultipartFile> imageFiles);

    Mono<String> saveVideoFile(MultipartFile videoFile);

    Mono<List<String>> saveVideoFiles(List<MultipartFile> videoFiles);
}
