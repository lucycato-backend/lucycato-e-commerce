package com.lucycato.eventservice.application.port.in;

import com.lucycato.eventservice.application.port.in.commend.AddCSATGradeQueueCommend;
import com.lucycato.eventservice.domain.UserRank;

public interface CSATGradeUseCase {

    UserRank addCASTGradeQueue(AddCSATGradeQueueCommend addCSATGradeQueueCommend);
}
