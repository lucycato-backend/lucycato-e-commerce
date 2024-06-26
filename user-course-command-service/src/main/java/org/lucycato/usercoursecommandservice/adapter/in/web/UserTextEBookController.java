package org.lucycato.usercoursecommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class UserTextEBookController {

    @PostMapping("api/admin/usercourse/v1/text-e-books")
    public void registerTextEBook() {

    }

    @PatchMapping("api/admin/usercourse/v1/text-e-books/{textEBookId}")
    public void modifyTextEBook() {

    }

    @DeleteMapping("api/admin/usercourse/v1/text-e-books/{textEBookId}")
    public void deleteTextEBook() {

    }

}
