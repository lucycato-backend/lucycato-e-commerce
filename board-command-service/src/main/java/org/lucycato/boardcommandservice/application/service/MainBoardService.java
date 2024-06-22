package org.lucycato.boardcommandservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.boardcommandservice.application.port.in.MainBoardUseCase;
import org.lucycato.boardcommandservice.application.port.in.command.CreateMainBoardCommand;
import org.lucycato.boardcommandservice.application.port.out.MainNoticePort;
import org.lucycato.boardcommandservice.application.port.out.result.CUDReturnIdResult;

import org.lucycato.boardcommandservice.application.port.out.result.MainBoardResult;
import org.lucycato.boardcommandservice.domain.CUDReturnId;
import org.lucycato.boardcommandservice.domain.MainBoard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MainBoardService implements MainBoardUseCase {

    private final MainNoticePort mainNoticePort;
    @Override
    public MainBoard createMainBoard(CreateMainBoardCommand command) {
        MainBoardResult mainBoardResult = mainNoticePort.CreateMainNotice(
                command.getUserId(),
                command.getTitle(),
                command.getContent(),
                command.getType()
        );
        return MainBoard.from(mainBoardResult);
    }
}
