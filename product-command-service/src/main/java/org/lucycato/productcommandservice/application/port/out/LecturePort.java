package org.lucycato.productcommandservice.application.port.out;

import org.lucycato.productcommandservice.application.port.out.result.LectureDetailResult;
import org.lucycato.productcommandservice.domain.enums.LectureCategory;
import org.lucycato.productcommandservice.domain.enums.LectureStatus;

public interface LecturePort {

    LectureDetailResult registerLecture(
            Long courseId,
            String lectureTitle,
            String lectureDescription,
            String lectureThumbnailImageUrl,
            String lectureVideoUrl,
            LectureCategory lectureCategory,
            LectureStatus lectureStatus
    );

    LectureDetailResult modifyLecture(
            Long lectureId,
            Long courseId,
            String lectureTitle,
            String lectureDescription,
            String lectureThumbnailImageUrl,
            String lectureVideoUrl,
            LectureCategory lectureCategory
    );

    void deleteLecture(Long lectureId);
}
