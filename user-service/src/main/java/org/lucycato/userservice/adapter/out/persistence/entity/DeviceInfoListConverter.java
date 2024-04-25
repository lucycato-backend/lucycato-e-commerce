package org.lucycato.userservice.adapter.out.persistence.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.userservice.model.info.DeviceInfo;

import java.util.List;

@Converter
@RequiredArgsConstructor
public class DeviceInfoListConverter implements AttributeConverter<List<DeviceInfo>, String> {
    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<DeviceInfo> deviceInfos) {
        try {
            return objectMapper.writeValueAsString(deviceInfos);
        } catch (Exception e) {
            throw new ApiExceptionImpl(ErrorCodeImpl.JSON_PARSING);
        }
    }

    @Override
    public List<DeviceInfo> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, new TypeReference<List<DeviceInfo>>() {
            });
        } catch (Exception e) {
            throw new ApiExceptionImpl(ErrorCodeImpl.JSON_PARSING);
        }
    }
}
