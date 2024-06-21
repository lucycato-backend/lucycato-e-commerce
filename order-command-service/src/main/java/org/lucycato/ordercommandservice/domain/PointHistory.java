package org.lucycato.ordercommandservice.domain;

import org.lucycato.ordercommandservice.domain.enums.PointHistoryStatus;

import java.math.BigDecimal;

public class PointHistory {
    private Long pointHistoryId;
    private PointHistoryStatus status;
    private Long userId;
    private BigDecimal usePoint;
    private BigDecimal leftPoint;
}
