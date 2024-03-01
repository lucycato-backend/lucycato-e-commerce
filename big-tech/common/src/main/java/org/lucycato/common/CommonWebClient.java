package org.lucycato.common;

import org.lucycato.common.error.ErrorCodeImpl;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;

@Component
public class CommonWebClient {
    private final WebClient webClient;

    public CommonWebClient() {
        this.webClient = WebClient.builder().build();
    }

    public Mono<String> sendGetRequestResultMono(String url)  {
        return webClient.get()
                .uri(URI.create(url))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(ErrorCodeImpl.CLIENT.build()))
                .bodyToMono(String.class);
    }

    public Flux<String> sendGetRequestResultFlux(String url) {
        return webClient.get()
                .uri(URI.create(url))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(ErrorCodeImpl.CLIENT.build()))
                .bodyToFlux(String.class);
    }

    public Mono<String> sendPostRequestResultMono(String url, Map<String, Object> body) {
        return webClient.post()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(ErrorCodeImpl.CLIENT.build()))
                .bodyToMono(String.class);
    }

    public Flux<String> sendPostRequestResultFlux(String url, Map<String, Object> body) {
        return webClient.post()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(ErrorCodeImpl.CLIENT.build()))
                .bodyToFlux(String.class);
    }

    public Mono<String> sendPutRequestResultMono(String url, Map<String, Object> body) {
        return webClient.put()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(ErrorCodeImpl.CLIENT.build()))
                .bodyToMono(String.class);
    }

    public Flux<String> sendPutRequestResultFlux(String url, Map<String, Object> body) {
        return webClient.put()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(ErrorCodeImpl.CLIENT.build()))
                .bodyToFlux(String.class);
    }

    public Mono<String> sendDeleteRequestResultMono(String url)  {
        return webClient.delete()
                .uri(URI.create(url))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(ErrorCodeImpl.CLIENT.build()))
                .bodyToMono(String.class);
    }

    public Flux<String> sendDeleteRequestResultFlux(String url) {
        return webClient.delete()
                .uri(URI.create(url))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> Mono.error(ErrorCodeImpl.CLIENT.build()))
                .bodyToFlux(String.class);
    }
}
