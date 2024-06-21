package org.lucycato.productcommandservice.application.port.out;

import org.lucycato.productcommandservice.application.port.out.result.CourseDetailResult;
import org.lucycato.productcommandservice.domain.enums.CourseGenre;
import org.lucycato.productcommandservice.domain.enums.CourseStatus;
import org.lucycato.productcommandservice.domain.enums.ProductBroadcastingCategory;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;

import java.time.LocalDateTime;
import java.util.function.BinaryOperator;

public interface CoursePort {

    CourseDetailResult registerCourse(
        Long courseSeriesId,
        String courseTitle,
        String courseSubTitle,
        Integer coursePrice,
        String courseImageUrl,
        String courseDescription,
        CourseGenre courseGenre,
        SubjectCategory subjectCategory,
        CourseStatus courseStatus,
        LocalDateTime courseExpiredAt
    );

    CourseDetailResult modifyCourse(
            Long courseId,
            Long courseSeriesId,
            String courseTitle,
            String courseSubTitle,
            Integer coursePrice,
            String courseImageUrl,
            String courseDescription,
            CourseGenre courseGenre,
            SubjectCategory subjectCategory,
            LocalDateTime courseExpiredAt
    );

    Boolean registerRecentCourseOpen(
            Long courseId,
            Long teacherId
    );

    Boolean getRecentCourseOpen(Long courseId);

    void sendRecentCourseOpen(
            Long courseId,
            Long teacherId,
            ProductBroadcastingCategory productBroadcastingCategory
    );

    void deleteCourse(Long courseId);
}
