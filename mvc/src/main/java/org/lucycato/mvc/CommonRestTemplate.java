package org.lucycato.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.context.XHeaderContext;
import org.lucycato.common.api.ErrorResponse;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@RequiredArgsConstructor
public class CommonRestTemplate {
    private final ObjectMapper objectMapper;

    private final RestTemplate restTemplate;

    public <T> T sendGetRequest(String url, Class<T> type) throws Exception {
        HttpHeaders headers = createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return validateStatusCodeAndReturnResponse(response, type);
    }

    public <T> T sendPostRequest(String url, String body, Class<T> type) throws Exception {
        HttpHeaders headers = createHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return validateStatusCodeAndReturnResponse(response, type);
    }

    public <T> T sendPatchRequest(String url, String body, Class<T> type) throws Exception {
        HttpHeaders headers = createHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        return validateStatusCodeAndReturnResponse(response, type);
    }

    public <T> T sendDeleteRequest(String url, Class<T> type) throws Exception {
        HttpHeaders headers = createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
        return validateStatusCodeAndReturnResponse(response, type);
    }

    private HttpHeaders createHeaders() {
        RequestAttributes requestContext = RequestContextHolder.getRequestAttributes();
        String adminOrAppMemberJsonStringHeader = requestContext != null ?
                (String) requestContext.getAttribute(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY, RequestAttributes.SCOPE_REQUEST) : "";

        HttpHeaders headers = new HttpHeaders();
        if (adminOrAppMemberJsonStringHeader != null && !adminOrAppMemberJsonStringHeader.isEmpty()) {
            headers.set(XHeaderContext.ADMIN_OR_APP_USER_JSON_STRING_HEADER_KEY, adminOrAppMemberJsonStringHeader);
        }
        return headers;
    }

    private <T> T validateStatusCodeAndReturnResponse(ResponseEntity<String> response, Class<T> type) throws Exception {
        boolean isSuccess = response.getStatusCode().is2xxSuccessful();
        if (isSuccess) {
            return objectMapper.readValue(response.getBody(), type);
        } else {
            ErrorResponse<T> ERRORResponseErrorReason = objectMapper.readValue(response.getBody(), new TypeReference<>() {});
            throw new ApiExceptionImpl(response.getStatusCode().value(), ERRORResponseErrorReason.getResult());
        }
    }
}
