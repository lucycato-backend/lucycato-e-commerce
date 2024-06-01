package com.lucycato.eventservice.adapter.out.persistence;

import com.lucycato.eventservice.application.port.out.CSARGradePort;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;

@PersistenceAdapter
@RequiredArgsConstructor
public class CSARGradePersistence implements CSARGradePort {
    @Override
    public String addCSARGradeWaitQueue(Long appUserId) {
        return "999";
    }
}
