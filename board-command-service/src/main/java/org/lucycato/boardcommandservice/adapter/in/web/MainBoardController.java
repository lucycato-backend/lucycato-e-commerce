package org.lucycato.boardcommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.boardcommandservice.adapter.in.web.requset.MainNoticeRequest;
import org.lucycato.boardcommandservice.application.port.in.MainBoardUseCase;
import org.lucycato.boardcommandservice.application.port.in.command.CreateMainBoardCommand;
import org.lucycato.boardcommandservice.domain.CUDReturnId;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AppUserHeaders;
import org.lucycato.common.resolver.AppUserHeaderDetail;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping
@RequiredArgsConstructor
public class MainBoardController {

    private final MainBoardUseCase mainBoardUseCase;

    @PostMapping("api/app/v1/main-board")
    public CUDReturnId createMainBoard(
            @RequestBody MainNoticeRequest request
//            @AppUserHeaders AppUserHeaderDetail appUserHeaderDetail
    ) {
        CreateMainBoardCommand command = new CreateMainBoardCommand(
               1L,
                request.getTitle(),
                request.getContent(),
                request.getType()
        );
        return mainBoardUseCase.createMainBoard(command);

    }
}
