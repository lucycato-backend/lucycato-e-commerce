package org.lucycato.boardcommandservice.application.port.out;

import org.lucycato.boardcommandservice.application.port.out.result.CUDReturnIdResult;

public interface MainNoticePort {

    CUDReturnIdResult CreateMainNotice(Long userId, String title, String content, String type);
}
