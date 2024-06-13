package org.lucycato.productcommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class CourseSeriesController {
    @PostMapping("api/admin/v1/course-series")
    public void registerCourseSeries() {

    }

    @PostMapping("api/admin/v1/course-series/{courseSeriesId}/courses/{courseId}")
    public void addCourseToCourseSeries() {

    }

    @PatchMapping("api/admin/v1/course-series/{courseId}")
    public void modifyCourseSeries() {

    }

    @DeleteMapping("api/admin/v1/course-series/{courseSeriesId}/courses/{courseId}")
    public void deleteCourseToCourseSeries() {

    }

    @DeleteMapping("api/admin/v1/course-series/{courseSeriesId}")
    public void deleteCourseSeries() {

    }
}
