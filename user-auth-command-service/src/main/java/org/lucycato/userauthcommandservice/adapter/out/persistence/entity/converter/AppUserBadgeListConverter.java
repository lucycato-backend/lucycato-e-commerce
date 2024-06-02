package org.lucycato.userauthcommandservice.adapter.out.persistence.entity.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.userauthcommandservice.domain.enums.AppUserBadge;

import java.util.List;

@Converter
@RequiredArgsConstructor
public class AppUserBadgeListConverter implements AttributeConverter<List<AppUserBadge>, String> {
    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<AppUserBadge> appUserBadges) {
        try {
            return objectMapper.writeValueAsString(appUserBadges);
        } catch (Exception e) {
            throw new ApiExceptionImpl(ErrorCodeImpl.JSON_PARSING);
        }
    }

    @Override
    public List<AppUserBadge> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, new TypeReference<>() {});
        } catch (Exception e) {
            throw new ApiExceptionImpl(ErrorCodeImpl.JSON_PARSING);
        }
    }
}
