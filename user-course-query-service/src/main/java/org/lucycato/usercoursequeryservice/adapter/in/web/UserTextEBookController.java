package org.lucycato.usercoursequeryservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AdminUserHeaders;
import org.lucycato.common.annotation.resolver.AppUserHeaders;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.lucycato.common.resolver.AppUserHeaderDetail;
import org.lucycato.usercoursequeryservice.domain.TextEBook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class UserTextEBookController {

    @GetMapping("open-api/usercourse/v1/text-e-books")
    public Flux<TextEBook> getTextEBooks() {
        return Flux.empty();
    }

    @GetMapping("api/app/usercourse/v1/text-e-books")
    public Flux<TextEBook> getAuthTextEBooks(@AppUserHeaders AppUserHeaderDetail appUserHeaderDetail) {
        return Flux.empty();
    }

    @GetMapping("api/admin/usercourse/v1/text-e-books")
    public Flux<TextEBook> getAuthTextEBooks(@AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail) {
        return Flux.empty();
    }
}
