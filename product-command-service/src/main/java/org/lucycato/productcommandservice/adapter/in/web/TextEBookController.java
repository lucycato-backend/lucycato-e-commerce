package org.lucycato.productcommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class TextEBookController {

    @PostMapping("api/admin/product/v1/text-e-books")
    public void registerTextEBook() {

    }

    @PatchMapping("api/admin/product/v1/text-e-books/{textEBookId}")
    public void modifyTextEBook() {

    }

    @DeleteMapping("api/admin/product/v1/text-e-books/{textEBookId}")
    public void deleteTextEBook() {

    }

}
