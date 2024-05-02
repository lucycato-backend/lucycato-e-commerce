package org.lucycato.userservice.adapter.out.persistence.entity.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;

import java.util.List;

@Converter
@RequiredArgsConstructor
public class DeviceInfoListConverter implements AttributeConverter<List<DeviceVo>, String> {
    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<DeviceVo> deviceVos) {
        try {
            return objectMapper.writeValueAsString(deviceVos);
        } catch (Exception e) {
            throw new ApiExceptionImpl(ErrorCodeImpl.JSON_PARSING);
        }
    }

    @Override
    public List<DeviceVo> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, new TypeReference<List<DeviceVo>>() {
            });
        } catch (Exception e) {
            throw new ApiExceptionImpl(ErrorCodeImpl.JSON_PARSING);
        }
    }
}
