package org.lucycato.productcommandservice.application.port.out;

import org.lucycato.productcommandservice.application.port.out.result.CourseSeriesDetailResult;
import org.lucycato.productcommandservice.domain.enums.CourseSeriesCategory;
import org.lucycato.productcommandservice.domain.enums.CourseSeriesStatus;
import org.lucycato.productcommandservice.domain.enums.SubjectCategory;

import java.util.List;

public interface CourseSeriesPort {

    CourseSeriesDetailResult registerCourseSeries(
            Long teacherId,
            String courseSeriesImageUrl,
            String courseSeriesTitle,
            String courseSeriesDescription,
            List<String> courseSeriesExplainImageUrls,
            SubjectCategory subjectCategory,
            CourseSeriesCategory courseSeriesCategory,
            CourseSeriesStatus courseSeriesStatus
    );

    CourseSeriesDetailResult modifyCourseSeries(
            Long courseSeriesId,
            Long teacherId,
            String courseSeriesImageUrl,
            String courseSeriesTitle,
            String courseSeriesDescription,
            List<String> courseSeriesExplainImageUrls,
            SubjectCategory subjectCategory,
            CourseSeriesCategory courseSeriesCategory
    );

    void deleteCourseSeries(Long courseSeries);
}
