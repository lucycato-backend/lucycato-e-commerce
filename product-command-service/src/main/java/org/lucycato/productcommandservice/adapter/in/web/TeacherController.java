package org.lucycato.productcommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.in.WebAdapter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class TeacherController {
    @PostMapping("api/admin/v1/teachers")
    public void registerTeacher() {
    }

    @PostMapping("api/admin/v1/teachers/{teacherId}/course-series")
    public void registerTeacherLectureSeries() {

    }

    @PostMapping("api/admin/v1/teachers/{teacherId}/courses")
    public void registerTeacherLecture() {

    }

    @PostMapping("api/admin/v1/teachers/{teacherId}/news")
    public void registerTeacherNews() {

    }

    @PatchMapping("api/admin/v1/teachers/{teacherId}")
    public void modifyTeacher() {

    }

    @PatchMapping("api/admin/v1/teachers/{teacherId}/course-series/{courseSeriesId}")
    public void modifyTeacherLectureSeries() {

    }

    @PatchMapping("api/admin/v1/teachers/{teacherId}/courses/{courseId}")
    public void modifyTeacherLecture() {

    }

    @PatchMapping("api/admin/v1/teachers/{teacherId}/news/{newsId}")
    public void modifyTeacherNews() {

    }

    @DeleteMapping("api/admin/v1/teachers/{teacherId}")
    public void deleteTeacher() {

    }
}
