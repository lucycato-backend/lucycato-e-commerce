package org.lucycato.productqueryservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AdminUserHeaders;
import org.lucycato.common.annotation.resolver.AppUserHeaders;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.lucycato.common.resolver.AppUserHeaderDetail;
import org.lucycato.productqueryservice.domain.Lecture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class LectureController {

    @GetMapping("open-api/product/v1/lectures")
    public Flux<Lecture> getLectures() {
        return Flux.empty();
    }

    @GetMapping("api/app/product/v1/lectures")
    public Flux<Lecture> getAuthLectures(@AppUserHeaders AppUserHeaderDetail appUserHeaderDetail) {
        return Flux.empty();
    }

    @GetMapping("api/admin/product/v1/lectures")
    public Flux<Lecture> getAuthLectures(@AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail) {
        return Flux.empty();
    }
}
