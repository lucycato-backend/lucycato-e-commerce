package org.lucycato.webflux;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.context.XHeaderContext;
import org.lucycato.common.api.ErrorResponse;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;

@Component
public class CommonWebClient {
    private static final String ALLOW_HEADER = "X";
    private final ObjectMapper objectMapper;
    private final WebClient webClient;

    public CommonWebClient(ObjectMapper objectMapper, WebClient.Builder webClientBuild) {
        this.objectMapper = objectMapper;
        this.webClient = webClientBuild.build();
    }

    public <T> Mono<T> sendGetRequestResultMono(String url, Class<T> type) {
        return Mono.deferContextual(contextView ->
                webClient.get()
                        .uri(URI.create(url))
                        .header(contextView.hasKey(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY) ? XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY : ALLOW_HEADER,
                                contextView.getOrDefault(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY, ALLOW_HEADER))
                        .retrieve()
                        .bodyToMono(String.class)
                        .flatMap(jsonString -> Mono.fromCallable(() -> objectMapper.readValue(jsonString, type)))
                        .onErrorResume(WebClientResponseException.class, ex ->
                                Mono.fromCallable(() -> objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<ErrorResponse<T>>() {
                                        }))
                                        .flatMap(apiErrorReason -> Mono.error(new ApiExceptionImpl(ex.getStatusCode().value(), apiErrorReason.getResult())))
                        )
                        .onErrorResume(Exception.class, Mono::error)
        );
    }

    public <T> Flux<T> sendGetRequestResultFlux(String url, Class<T> type) {
        return Flux.deferContextual(contextView ->
                webClient.get()
                        .uri(URI.create(url))
                        .header(contextView.hasKey(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY) ? XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY : ALLOW_HEADER,
                                contextView.getOrDefault(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY, ALLOW_HEADER))
                        .retrieve()
                        .bodyToFlux(String.class)
                        .flatMap(jsonString -> Mono.fromCallable(() -> objectMapper.readValue(jsonString, type)))
                        .onErrorResume(WebClientResponseException.class, ex ->
                                Mono.fromCallable(() -> objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<ErrorResponse<T>>() {
                                        }))
                                        .flatMap(apiErrorReason -> Mono.error(new ApiExceptionImpl(ex.getStatusCode().value(), apiErrorReason.getResult())))
                        )
                        .onErrorResume(Exception.class, Mono::error)
        );
    }

    public <T> Mono<T> sendPostRequestResultMono(String url, Map<String, Object> body, Class<T> type) {
        return Mono.deferContextual(contextView ->
                webClient.post()
                        .uri(URI.create(url))
                        .header("Content-Type", "application/json")
                        .header(contextView.hasKey(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY) ? XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY : "",
                                contextView.getOrDefault(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY, ""))
                        .bodyValue(body)
                        .retrieve()
                        .bodyToMono(String.class)
                        .flatMap(jsonString -> Mono.fromCallable(() -> objectMapper.readValue(jsonString, type)))
                        .onErrorResume(WebClientResponseException.class, ex ->
                                Mono.fromCallable(() -> objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<ErrorResponse<T>>() {
                                        }))
                                        .flatMap(apiErrorReason -> Mono.error(new ApiExceptionImpl(ex.getStatusCode().value(), apiErrorReason.getResult())))
                        )
                        .onErrorResume(Mono::error)
        );
    }

    public <T> Flux<T> sendPostRequestResultFlux(String url, Map<String, Object> body, Class<T> type) {
        return Flux.deferContextual(contextView ->
                webClient.post()
                        .uri(URI.create(url))
                        .header("Content-Type", "application/json")
                        .header(contextView.hasKey(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY) ? XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY : "",
                                contextView.getOrDefault(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY, ""))
                        .bodyValue(body)
                        .retrieve()
                        .bodyToFlux(String.class)
                        .flatMap(jsonString -> Mono.fromCallable(() -> objectMapper.readValue(jsonString, type)))
                        .onErrorResume(WebClientResponseException.class, ex ->
                                Mono.fromCallable(() -> objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<ErrorResponse<T>>() {
                                        }))
                                        .flatMap(apiErrorReason -> Mono.error(new ApiExceptionImpl(ex.getStatusCode().value(), apiErrorReason.getResult())))
                        )
                        .onErrorResume(Mono::error)
        );
    }

    public <T> Mono<T> sendPutRequestResultMono(String url, Map<String, Object> body, Class<T> type) {
        return Mono.deferContextual(contextView ->
                webClient.put()
                        .uri(URI.create(url))
                        .header("Content-Type", "application/json")
                        .header(contextView.hasKey(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY) ? XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY : "",
                                contextView.getOrDefault(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY, ""))
                        .bodyValue(body)
                        .retrieve()
                        .bodyToMono(String.class)
                        .flatMap(jsonString -> Mono.fromCallable(() -> objectMapper.readValue(jsonString, type)))
                        .onErrorResume(WebClientResponseException.class, ex ->
                                Mono.fromCallable(() -> objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<ErrorResponse<T>>() {
                                        }))
                                        .flatMap(apiErrorReason -> Mono.error(new ApiExceptionImpl(ex.getStatusCode().value(), apiErrorReason.getResult())))
                        )
                        .onErrorResume(Mono::error)
        );
    }

    public <T> Flux<T> sendPutRequestResultFlux(String url, Map<String, Object> body, Class<T> type) {
        return Flux.deferContextual(contextView ->
                webClient.put()
                        .uri(URI.create(url))
                        .header("Content-Type", "application/json")
                        .header(contextView.hasKey(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY) ? XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY : "",
                                contextView.getOrDefault(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY, ""))
                        .bodyValue(body)
                        .retrieve()
                        .bodyToFlux(String.class)
                        .flatMap(jsonString -> Mono.fromCallable(() -> objectMapper.readValue(jsonString, type)))
                        .onErrorResume(WebClientResponseException.class, ex ->
                                Mono.fromCallable(() -> objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<ErrorResponse<T>>() {
                                        }))
                                        .flatMap(apiErrorReason -> Mono.error(new ApiExceptionImpl(ex.getStatusCode().value(), apiErrorReason.getResult())))
                        )
                        .onErrorResume(Mono::error)
        );
    }

    public <T> Mono<T> sendDeleteRequestResultMono(String url, Class<T> type) {
        return Mono.deferContextual(contextView ->
                webClient.delete()
                        .uri(URI.create(url))
                        .header(contextView.hasKey(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY) ? XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY : ALLOW_HEADER,
                                contextView.getOrDefault(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY, ALLOW_HEADER))
                        .retrieve()
                        .bodyToMono(String.class)
                        .flatMap(jsonString -> Mono.fromCallable(() -> objectMapper.readValue(jsonString, type)))
                        .onErrorResume(WebClientResponseException.class, ex ->
                                Mono.fromCallable(() -> objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<ErrorResponse<T>>() {
                                        }))
                                        .flatMap(apiErrorReason -> Mono.error(new ApiExceptionImpl(ex.getStatusCode().value(), apiErrorReason.getResult())))
                        )
                        .onErrorResume(Mono::error)
        );
    }

    public <T> Flux<T> sendDeleteRequestResultFlux(String url, Class<T> type) {
        return Flux.deferContextual(contextView ->
                webClient.delete()
                        .uri(URI.create(url))
                        .header(contextView.hasKey(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY) ? XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY : ALLOW_HEADER,
                                contextView.getOrDefault(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY, ALLOW_HEADER))
                        .retrieve()
                        .bodyToFlux(String.class)
                        .flatMap(jsonString -> Mono.fromCallable(() -> objectMapper.readValue(jsonString, type)))
                        .onErrorResume(WebClientResponseException.class, ex ->
                                Mono.fromCallable(() -> objectMapper.readValue(ex.getResponseBodyAsString(), new TypeReference<ErrorResponse<T>>() {
                                        }))
                                        .flatMap(apiErrorReason -> Mono.error(new ApiExceptionImpl(ex.getStatusCode().value(), apiErrorReason.getResult())))
                        )
                        .onErrorResume(Mono::error)
        );
    }
}
