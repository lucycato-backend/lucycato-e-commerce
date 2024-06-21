package org.lucycato.productcommandservice.adapter.out.externalsystem;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.ExternalSystemAdapter;
import org.lucycato.productcommandservice.application.port.out.FileStoragePort;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class FileStorageExternalSystems implements FileStoragePort {

    @Override
    public String saveImageFile(MultipartFile imageFile) {
        return "http://";
    }

    @Override
    public List<String> saveImageFiles(List<MultipartFile> imageFiles) {
        return List.of("http://", "http://", "http://");
    }

    @Override
    public String saveVideoFile(MultipartFile videoFile) {
        return "http://";
    }

    @Override
    public String savePdfFile(MultipartFile pdfFile) {
        return "http://";
    }
}
