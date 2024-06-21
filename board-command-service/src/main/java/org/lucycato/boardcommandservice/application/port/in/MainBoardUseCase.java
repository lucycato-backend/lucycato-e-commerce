package org.lucycato.boardcommandservice.application.port.in;

import org.lucycato.boardcommandservice.application.port.in.command.CreateMainBoardCommand;
import org.lucycato.boardcommandservice.domain.CUDReturnId;
import org.lucycato.boardcommandservice.domain.MainBoard;

public interface MainBoardUseCase {

    MainBoard createMainBoard(CreateMainBoardCommand command);
}
