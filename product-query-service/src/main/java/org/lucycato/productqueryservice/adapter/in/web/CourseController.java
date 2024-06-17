package org.lucycato.productqueryservice.adapter.in.web;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.lucycato.common.annotation.resolver.AdminUserHeaders;
import org.lucycato.common.annotation.resolver.AppUserHeaders;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.lucycato.common.resolver.AppUserHeaderDetail;
import org.lucycato.productqueryservice.application.port.in.CourseUseCase;
import org.lucycato.productqueryservice.application.port.in.command.*;
import org.lucycato.productqueryservice.domain.*;
import org.springframework.boot.autoconfigure.sendgrid.SendGridAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseUseCase courseUseCase;

    @GetMapping("open-api/v1/courses/{courseId}")
    public Mono<CourseDetail> getCourse(
            @PathVariable
            Long courseId
    ) {
        CourseDetailSearchCommand command = new CourseDetailSearchCommand(
                courseId
        );
        return courseUseCase.getCurses(command);
    }

    @GetMapping("open-api/v1/courses/{courseId}/lectures")
    public Flux<CourseLecture> getCourseLectures(
            @PathVariable
            Long courseId
    ) {
        SpecificCourseLectureSearchCommand command = new SpecificCourseLectureSearchCommand(
                courseId
        );
        return courseUseCase.getCourseLectureList(command);
    }

    @GetMapping("api/admin/v1/courses/{courseId}/lectures")
    public Flux<CourseLecture> getAuthCourseLectures(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @PathVariable
            Long courseId
    ) {
        SpecificAdminCourseLectureSearchCommand command = new SpecificAdminCourseLectureSearchCommand(
                adminUserHeaderDetail.getAdminUserId(),
                courseId
        );
        return courseUseCase.getAuthCourseLectureList(command);
    }

    @GetMapping("api/app/v1/courses/{courseId}/lectures")
    public Flux<CourseLecture> getAuthCourseLectures(
            @AppUserHeaders
            AppUserHeaderDetail appUserHeaderDetail,
            @PathVariable
            Long courseId
    ) {
        SpecificAppCourseLectureSearchCommand command = new SpecificAppCourseLectureSearchCommand(
                appUserHeaderDetail.getAppUserId(),
                courseId
        );
        return courseUseCase.getAuthCourseLectureList(command);
    }

    @GetMapping("open-api/v1/courses/{courseId}/text-e-books")
    public Flux<CourseTextEBook> getCourseTextEBooks(
            @PathVariable
            Long courseId
    ) {
        SpecificCourseTextEBookSearchCommand command = new SpecificCourseTextEBookSearchCommand(
                courseId
        );
        return courseUseCase.getCourseTextEBookList(command);
    }

    @GetMapping("api/admin/v1/courses/{courseId}/text-e-books")
    public Flux<CourseTextEBook> getAuthCourseTextEBooks(
            @AdminUserHeaders
            AdminUserHeaderDetail adminUserHeaderDetail,
            @PathVariable
            Long courseId
    ) {
        SpecificAdminCourseTextEBookSearchCommand command = new SpecificAdminCourseTextEBookSearchCommand(
                adminUserHeaderDetail.getAdminUserId(),
                courseId
        );
        return courseUseCase.getAuthCourseTextEBookList(command);
    }

    @GetMapping("api/app/v1/courses/{courseId}/text-e-books")
    public Flux<CourseTextEBook> getAuthCourseTextEBooks(
            @AppUserHeaders
            AppUserHeaderDetail appUserHeaderDetail,
            @PathVariable
            Long courseId
    ) {
        SpecificAppCourseTextEBookSearchCommand command = new SpecificAppCourseTextEBookSearchCommand(
                appUserHeaderDetail.getAppUserId(),
                courseId
        );
        return courseUseCase.getAuthCourseTextEBookList(command);
    }
}
