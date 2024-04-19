package org.lucycato.webflux;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lucycato.common.XHeaderContext;
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

    public <T> Mono<T> sendGetRequestResultMono(String url) {
        return Mono.deferContextual(contextView ->
                webClient.get()
                        .uri(URI.create(url))
                        .header(contextView.hasKey(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY) ? XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY : "",
                                contextView.getOrDefault(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY, ""))
                        .retrieve()
                        .bodyToMono(String.class)
                        .flatMap(jsonString -> Mono.fromCallable(() -> objectMapper.readValue(jsonString, new TypeReference<T>() {
                        })))
                        .onErrorResume(WebClientResponseException.class, ex ->
                                Mono.fromCallable(() -> objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<Api<T>>() {
                                        }))
                                        .flatMap(apiErrorReason -> Mono.error(new ApiExceptionImpl(ex.getStatusCode().value(), apiErrorReason.getResult())))
                        )
                        .onErrorResume(error -> Mono.empty())
        );
    }

    public <T> Mono<T> sendPostRequestResultMono(String url, Map<String, Object> body) {
        return Mono.deferContextual(contextView ->
                webClient.post()
                        .uri(URI.create(url))
                        .header("Content-Type", "application/json")
                        .header(contextView.hasKey(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY) ? XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY : "",
                                contextView.getOrDefault(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY, ""))
                        .bodyValue(body)
                        .retrieve()
                        .bodyToMono(String.class)
                        .flatMap(jsonString -> Mono.fromCallable(() -> objectMapper.readValue(jsonString, new TypeReference<T>() {
                        })))
                        .onErrorResume(WebClientResponseException.class, ex ->
                                Mono.fromCallable(() -> objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<Api<T>>() {
                                        }))
                                        .flatMap(apiErrorReason -> Mono.error(new ApiExceptionImpl(ex.getStatusCode().value(), apiErrorReason.getResult())))
                        )
                        .onErrorResume(error -> Mono.empty())
        );
    }

    public <T> Mono<T> sendPutRequestResultMono(String url, Map<String, Object> body) {
        return Mono.deferContextual(contextView ->
                webClient.put()
                        .uri(URI.create(url))
                        .header("Content-Type", "application/json")
                        .header(contextView.hasKey(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY) ? XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY : "",
                                contextView.getOrDefault(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY, ""))
                        .bodyValue(body)
                        .retrieve()
                        .bodyToMono(String.class)
                        .flatMap(jsonString -> Mono.fromCallable(() -> objectMapper.readValue(jsonString, new TypeReference<T>() {
                        })))
                        .onErrorResume(WebClientResponseException.class, ex ->
                                Mono.fromCallable(() -> objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<Api<T>>() {
                                        }))
                                        .flatMap(apiErrorReason -> Mono.error(new ApiExceptionImpl(ex.getStatusCode().value(), apiErrorReason.getResult())))
                        )
                        .onErrorResume(error -> Mono.empty())
        );
    }

    public <T> Mono<T> sendDeleteRequestResultMono(String url) {
        return Mono.deferContextual(contextView ->
                webClient.delete()
                        .uri(URI.create(url))
                        .header(contextView.hasKey(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY) ? XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY : "",
                                contextView.getOrDefault(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY, ""))
                        .retrieve()
                        .bodyToMono(String.class)
                        .flatMap(jsonString -> Mono.fromCallable(() -> objectMapper.readValue(jsonString, new TypeReference<T>() {
                        })))
                        .onErrorResume(WebClientResponseException.class, ex ->
                                Mono.fromCallable(() -> objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<Api<T>>() {
                                        }))
                                        .flatMap(apiErrorReason -> Mono.error(new ApiExceptionImpl(ex.getStatusCode().value(), apiErrorReason.getResult())))
                        )
                        .onErrorResume(error -> Mono.empty())
        );
    }
}
