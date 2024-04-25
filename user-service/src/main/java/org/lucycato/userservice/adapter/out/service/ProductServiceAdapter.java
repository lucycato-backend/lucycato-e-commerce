package org.lucycato.userservice.adapter.out.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.ServiceAdapter;
import org.lucycato.userservice.application.port.out.ProductPort;

import java.util.Arrays;
import java.util.List;

@ServiceAdapter
@RequiredArgsConstructor
public class ProductServiceAdapter implements ProductPort {

    @Override
    public List<Long> getAppUserIdsByLectureIds(List<Long> lectureIds) {
        return Arrays.asList(1L, 2L);
    }

    @Override
    public List<Long> getAppUserIdsByTeacherIds(List<Long> teacherIds) {
        return Arrays.asList(1L, 2L);
    }
}
