package org.lucycato.memberservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.CommonWebClient;
import org.lucycato.common.api.Api;
import org.lucycato.common.error.ErrorCodeImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@SpringBootApplication
public class MemberServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberServiceApplication.class, args);
    }

    private final ObjectMapper objectMapper;
    private final CommonWebClient commonWebClient;

    @GetMapping
    public Mono<Api<User>> test() {
        String url = "http://event-service:8080";
        return commonWebClient.sendGetRequestResultMono(url)
                .map(it -> {
                    return Api.OK(new User());
                })
                .onErrorResume(WebClientResponseException.class, ex -> {
                    try {
                        Api<User> value = objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<>() {});
                        return Mono.just(value);
                    } catch (Exception e) {
                        throw ErrorCodeImpl.REQUEST_CLIENT.build();
                    }
                });
    }
}