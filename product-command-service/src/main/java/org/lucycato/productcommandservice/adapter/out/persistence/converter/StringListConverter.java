package org.lucycato.productcommandservice.adapter.out.persistence.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;

import java.util.List;

@Converter
@RequiredArgsConstructor
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        try {
            return objectMapper.writeValueAsString(strings);
        } catch (Exception e) {
            throw new ApiExceptionImpl(ErrorCodeImpl.JSON_PARSING);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, new TypeReference<>() {});
        } catch (Exception e) {
            throw new ApiExceptionImpl(ErrorCodeImpl.JSON_PARSING);
        }
    }
}
