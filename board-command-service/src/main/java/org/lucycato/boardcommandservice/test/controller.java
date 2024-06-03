package org.lucycato.boardcommandservice.test;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class controller {

    @GetMapping("hello")
    public String hello() {
        System.out.println("Hell0123");
        return "hello1231";
    }
}
