package org.lucycato.usercoursecommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class CourseController {
    @PostMapping("api/admin/usercourse/v1/courses")
    public void registerCourse() {

    }

    @PostMapping("api/admin/usercourse/v1/courses/{coursesId}/lectures")
    public void registerCourseLecture(@PathVariable Long coursesId) {

    }

    @PostMapping("api/admin/usercourse/v1/courses/{courseId}/text-e-books")
    public void registerCourseTextEBook(@PathVariable Long courseId) {

    }

    @PostMapping("api/app/usercourse/v1/courses/{courseId}/reviews")
    public void registerCourseReview() {

    }

    @PatchMapping("api/admin/usercourse/v1/courses/{courseId}")
    public void modifyCourse() {

    }

    @PatchMapping("api/admin/usercourse/v1/courses/{courseId}/lecture/{lectureId}")
    public void modifyCourseLecture(@PathVariable Long courseId, @PathVariable Long lectureId) {

    }

    @PatchMapping("api/admin/usercourse/v1/courses/{courseId}/text-e-books/{textEBookId}")
    public void modifyCourseTextEBook(@PathVariable Long courseId, @PathVariable Long lectureId) {

    }

    @PatchMapping("api/app/usercourse/v1/courses/{courseId}/reviews/{reviewId}")
    public void modifyCourseReview() {

    }

    @DeleteMapping("api/app/usercourse/v1/courses/{courseId}")
    public void deleteCourseLecture() {

    }
}
