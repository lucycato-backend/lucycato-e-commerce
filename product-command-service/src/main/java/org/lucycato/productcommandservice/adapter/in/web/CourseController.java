package org.lucycato.productcommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class CourseController {
    @PostMapping("api/admin/product/v1/courses")
    public void registerCourse() {

    }

    @PostMapping("api/admin/product/v1/courses/{coursesId}/lectures")
    public void registerCourseLecture(@PathVariable Long coursesId) {

    }

    @PostMapping("api/admin/product/v1/courses/{courseId}/text-e-books")
    public void registerCourseTextEBook(@PathVariable Long courseId) {

    }

    @PostMapping("api/app/product/v1/courses/{courseId}/reviews")
    public void registerCourseReview() {

    }

    @PatchMapping("api/admin/product/v1/courses/{courseId}")
    public void modifyCourse() {

    }

    @PatchMapping("api/admin/product/v1/courses/{courseId}/lecture/{lectureId}")
    public void modifyCourseLecture(@PathVariable Long courseId, @PathVariable Long lectureId) {

    }

    @PatchMapping("api/admin/product/v1/courses/{courseId}/text-e-books/{textEBookId}")
    public void modifyCourseTextEBook(@PathVariable Long courseId, @PathVariable Long lectureId) {

    }

    @PatchMapping("api/app/product/v1/courses/{courseId}/reviews/{reviewId}")
    public void modifyCourseReview() {

    }

    @DeleteMapping("api/app/product/v1/courses/{courseId}")
    public void deleteCourseLecture() {

    }

    @GetMapping("")
    public String test() {
        System.out.println("Hello Jiny");
        return "Hello Jiny";
    }
}
