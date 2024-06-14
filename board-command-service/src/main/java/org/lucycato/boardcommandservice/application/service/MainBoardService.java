package org.lucycato.boardcommandservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.boardcommandservice.application.port.in.MainBoardUseCase;
import org.lucycato.boardcommandservice.application.port.in.command.CreateMainBoardCommand;
import org.lucycato.boardcommandservice.application.port.out.MainNoticePort;
import org.lucycato.boardcommandservice.application.port.out.result.CUDReturnIdResult;

import org.lucycato.boardcommandservice.domain.CUDReturnId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MainBoardService implements MainBoardUseCase {

    private final MainNoticePort mainNoticePort;
    @Override
    public CUDReturnId createMainBoard(CreateMainBoardCommand command) {
        CUDReturnIdResult cudReturnIdResult = mainNoticePort.CreateMainNotice(
                command.getUserId(),
                command.getTitle(),
                command.getContent(),
                command.getType()
        );
        return CUDReturnId.from(cudReturnIdResult);
    }
}
