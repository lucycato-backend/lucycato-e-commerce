package com.lucycato.eventservice.adapter.in.web;

import com.lucycato.eventservice.application.port.in.CSATGradeUseCase;
import com.lucycato.eventservice.application.port.in.commend.AddCSATGradeQueueCommend;
import com.lucycato.eventservice.domain.UserRank;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AdminUserHeaders;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping
@RequiredArgsConstructor
public class CSATGradeController {

    private final CSATGradeUseCase csatGradeUseCase;

    @PostMapping("open-api/app/event-service/v1/wait-queue")
    public UserRank addWaitQueue(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail
    ) {
        AddCSATGradeQueueCommend commend = new AddCSATGradeQueueCommend(adminUserHeaderDetail.getAdminUserId());
        return csatGradeUseCase.addCASTGradeQueue(commend);
    }

}
