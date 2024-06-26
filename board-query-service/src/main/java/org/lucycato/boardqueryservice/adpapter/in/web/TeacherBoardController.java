package org.lucycato.boardqueryservice.adpapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.boardqueryservice.application.port.in.TeacherBoardUseCase;
import org.lucycato.boardqueryservice.application.port.in.command.GetTeacherNoticeCommand;
import org.lucycato.boardqueryservice.domain.RecentTeacherNotice;
import org.lucycato.boardqueryservice.domain.TeacherNotice;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class TeacherBoardController {

    private final TeacherBoardUseCase teacherBoardUseCase;

    @GetMapping("open-api/v1/teacher/{teacherId}/recent2")
    public Mono<RecentTeacherNotice> getRecentTeacherNotice(@PathVariable long teacherId) {
            GetTeacherNoticeCommand command = new GetTeacherNoticeCommand(teacherId);
        return teacherBoardUseCase.getRecentTeacherNotice(command);
    }


    public Flux<TeacherNotice> getTeacherNotice(
            @PathVariable
            Long teacherId
    ) {
        GetTeacherNoticeCommand command = new GetTeacherNoticeCommand(teacherId);

        return null;//teacherBoardUseCase.getTeacherNotice(command);
    }

//    @GetMapping("api/admin/board/v1/teachers/notices/by-teacher/{teacherId}")
//    public Flux<TeacherNotice> getTeacherNotice(
//            @PathVariable
//            Long teacherId
//    ) {
//
//    }
//
//    @GetMapping("api/app/board/v1/teachers/notices/by-teacher/{teacherId}")
//    public Flux<TeacherNotice> getTeacherNotice(
//            @PathVariable
//            Long teacherId
//    ) {
//
//    }
//
//    @GetMapping()

}
