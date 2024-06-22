package org.lucycato.boardcommandservice.application.port.out;

import org.lucycato.boardcommandservice.application.port.out.result.CUDReturnIdResult;
import org.lucycato.boardcommandservice.application.port.out.result.MainBoardResult;

public interface MainNoticePort {

    MainBoardResult CreateMainNotice(Long userId, String title, String content, String type);
}
