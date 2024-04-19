package org.lucycato.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lucycato.common.XHeaderContext;
import org.lucycato.common.api.Erroresponse;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class CommonHttpClient {
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public CommonHttpClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.httpClient = HttpClient.newBuilder().build();
    }

    public <T> T sendGetRequest(String url) throws Exception {
        RequestAttributes requestContext = RequestContextHolder.getRequestAttributes();
        String adminOrAppMemberJsonStringHeader = requestContext != null ? (String) requestContext.getAttribute(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY, RequestAttributes.SCOPE_REQUEST) : "";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(adminOrAppMemberJsonStringHeader == null || adminOrAppMemberJsonStringHeader.isEmpty() ? "" : XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY,
                        adminOrAppMemberJsonStringHeader == null || adminOrAppMemberJsonStringHeader.isEmpty() ? "" : adminOrAppMemberJsonStringHeader)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return validateStatusCodeAndReturnResponse(response);
    }

    public <T> T sendPostRequest(String url, String body) throws Exception {
        RequestAttributes requestContext = RequestContextHolder.getRequestAttributes();
        String adminOrAppMemberJsonStringHeader = requestContext != null ? (String) requestContext.getAttribute(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY, RequestAttributes.SCOPE_REQUEST) : "";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header(adminOrAppMemberJsonStringHeader == null || adminOrAppMemberJsonStringHeader.isEmpty() ? "" : XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY,
                        adminOrAppMemberJsonStringHeader == null || adminOrAppMemberJsonStringHeader.isEmpty() ? "" : adminOrAppMemberJsonStringHeader)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return validateStatusCodeAndReturnResponse(response);
    }

    public <T> T sendPutRequest(String url, String body) throws Exception {
        RequestAttributes requestContext = RequestContextHolder.getRequestAttributes();
        String adminOrAppMemberJsonStringHeader = requestContext != null ? (String) requestContext.getAttribute(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY, RequestAttributes.SCOPE_REQUEST) : "";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header(adminOrAppMemberJsonStringHeader == null || adminOrAppMemberJsonStringHeader.isEmpty() ? "" : XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY,
                        adminOrAppMemberJsonStringHeader == null || adminOrAppMemberJsonStringHeader.isEmpty() ? "" : adminOrAppMemberJsonStringHeader)
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return validateStatusCodeAndReturnResponse(response);
    }

    public <T> T sendDeleteRequest(String url) throws Exception {
        RequestAttributes requestContext = RequestContextHolder.getRequestAttributes();
        String adminOrAppMemberJsonStringHeader = requestContext != null ? (String) requestContext.getAttribute(XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY, RequestAttributes.SCOPE_REQUEST) : "";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(adminOrAppMemberJsonStringHeader == null || adminOrAppMemberJsonStringHeader.isEmpty() ? "" : XHeaderContext.ADMIN_OR_APP_MEMBER_JSON_STRING_HEADER_KEY,
                        adminOrAppMemberJsonStringHeader == null || adminOrAppMemberJsonStringHeader.isEmpty() ? "" : adminOrAppMemberJsonStringHeader)
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return validateStatusCodeAndReturnResponse(response);
    }

    private <T> T validateStatusCodeAndReturnResponse(HttpResponse<String> response) throws Exception {
        boolean isSuccess = response.statusCode() >= 200 && response.statusCode() < 300;
        if (isSuccess) {
            return objectMapper.readValue(response.body(), new TypeReference<T>() {
            });
        } else {
            Erroresponse<T> ERRORResponseErrorReason = objectMapper.readValue(response.body(), new TypeReference<>() {});
            throw new ApiExceptionImpl(response.statusCode(), ERRORResponseErrorReason.getResult());
        }
    }
}
