package org.lucycato.boardcommandservice.application.port.in;

import org.lucycato.boardcommandservice.application.port.in.command.CreateMainBoardCommand;
import org.lucycato.boardcommandservice.domain.CUDReturnId;

public interface MainBoardUseCase {

    CUDReturnId createMainBoard(CreateMainBoardCommand command);
}
