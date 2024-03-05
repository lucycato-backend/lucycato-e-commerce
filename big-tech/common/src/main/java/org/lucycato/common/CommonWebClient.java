package org.lucycato.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lucycato.common.api.Api;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;

@Component
public class CommonWebClient {
    private final ObjectMapper objectMapper;
    private final WebClient webClient;

    public CommonWebClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.webClient = WebClient.builder().build();
    }

    public <T> Mono<Api<T>> sendGetRequestResultMono(String url)  {
        return webClient.get()
                .uri(URI.create(url))
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(jsonString -> Mono.fromCallable(() -> objectMapper.readValue(jsonString, new TypeReference<Api<T>>() {})))
                .onErrorResume(WebClientRequestException.class, ex -> Mono.empty())
                .onErrorResume(WebClientResponseException.class, ex ->
                    Mono.fromCallable(() -> objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<Api<T>>() {}))
                            .flatMap(apiErrorReason -> Mono.error(new ApiExceptionImpl(ex.getStatusCode().value(), apiErrorReason.getResult())))
                );
    }

    public <T> Mono<Api<T>> sendPostRequestResultMono(String url, Map<String, Object> body) {
        return webClient.post()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(jsonString -> Mono.fromCallable(() -> objectMapper.readValue(jsonString, new TypeReference<Api<T>>() {})))
                .onErrorResume(WebClientRequestException.class, ex -> Mono.empty())
                .onErrorResume(WebClientResponseException.class, ex ->
                        Mono.fromCallable(() -> objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<Api<T>>() {}))
                                .flatMap(apiErrorReason -> Mono.error(new ApiExceptionImpl(ex.getStatusCode().value(), apiErrorReason.getResult())))
                );
    }

    public <T> Mono<Api<T>> sendPutRequestResultMono(String url, Map<String, Object> body) {
        return webClient.put()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(jsonString -> Mono.fromCallable(() -> objectMapper.readValue(jsonString, new TypeReference<Api<T>>() {})))
                .onErrorResume(WebClientRequestException.class, ex -> Mono.empty())
                .onErrorResume(WebClientResponseException.class, ex ->
                        Mono.fromCallable(() -> objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<Api<T>>() {}))
                                .flatMap(apiErrorReason -> Mono.error(new ApiExceptionImpl(ex.getStatusCode().value(), apiErrorReason.getResult())))
                );
    }

    public <T> Mono<Api<T>> sendDeleteRequestResultMono(String url)  {
        return webClient.delete()
                .uri(URI.create(url))
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(jsonString -> Mono.fromCallable(() -> objectMapper.readValue(jsonString, new TypeReference<Api<T>>() {})))
                .onErrorResume(WebClientRequestException.class, ex -> Mono.empty())
                .onErrorResume(WebClientResponseException.class, ex ->
                        Mono.fromCallable(() -> objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<Api<T>>() {}))
                                .flatMap(apiErrorReason -> Mono.error(new ApiExceptionImpl(ex.getStatusCode().value(), apiErrorReason.getResult())))
                );
    }
}
