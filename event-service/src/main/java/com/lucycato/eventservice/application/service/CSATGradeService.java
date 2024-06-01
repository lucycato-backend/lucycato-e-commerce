package com.lucycato.eventservice.application.service;

import com.lucycato.eventservice.application.port.in.CSATGradeUseCase;
import com.lucycato.eventservice.application.port.in.commend.AddCSATGradeQueueCommend;
import com.lucycato.eventservice.application.port.out.CSARGradePort;
import com.lucycato.eventservice.domain.UserRank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CSATGradeService implements CSATGradeUseCase {

    private final CSARGradePort csarGradePort;

    @Override
    public UserRank addCASTGradeQueue(AddCSATGradeQueueCommend addCSATGradeQueueCommend) {
        String result =  csarGradePort.addCSARGradeWaitQueue(addCSATGradeQueueCommend.getAppUserId());
        return UserRank.from(result);
    }
}
