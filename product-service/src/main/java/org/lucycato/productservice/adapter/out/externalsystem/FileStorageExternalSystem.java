package org.lucycato.productservice.adapter.out.externalsystem;

import org.lucycato.common.annotation.hexagonal.out.ExternalSystemAdapter;
import org.lucycato.productservice.application.port.out.FileStorePort;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.List;

@ExternalSystemAdapter
public class FileStorageExternalSystem implements FileStorePort {
    @Override
    public Mono<String> saveImageFile(MultipartFile imageFile) {
        return null;
    }

    @Override
    public Mono<List<String>> saveImageFiles(List<MultipartFile> imageFiles) {
        return null;
    }

    @Override
    public Mono<String> saveVideoFile(MultipartFile videoFile) {
        return null;
    }

    @Override
    public Mono<List<String>> saveVideoFiles(List<MultipartFile> videoFiles) {
        return null;
    }
}
