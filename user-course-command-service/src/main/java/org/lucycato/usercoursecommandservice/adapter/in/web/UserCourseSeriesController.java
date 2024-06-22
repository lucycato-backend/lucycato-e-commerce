package org.lucycato.usercoursecommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class UserCourseSeriesController {
    @PostMapping("api/admin/usercourse/v1/course-series")
    public void registerCourseSeries() {

    }

    @PostMapping("api/admin/usercourse/v1/course-series/{courseSeriesId}/courses/{courseId}")
    public void addCourseToCourseSeries() {

    }

    @PatchMapping("api/admin/usercourse/v1/course-series/{courseId}")
    public void modifyCourseSeries() {

    }

    @DeleteMapping("api/admin/usercourse/v1/course-series/{courseSeriesId}/courses/{courseId}")
    public void deleteCourseToCourseSeries() {

    }

    @DeleteMapping("api/admin/usercourse/v1/course-series/{courseSeriesId}")
    public void deleteCourseSeries() {

    }
}
