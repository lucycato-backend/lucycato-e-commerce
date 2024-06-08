package org.lucycato.usercoursequeryservice.application.port.in.command;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.usercoursequeryservice.domain.enums.CourseGenre;
import org.lucycato.usercoursequeryservice.domain.enums.SubjectCategory;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CourseSearchCommand extends SelfValidating<CourseSearchCommand> {
    private CourseGenre courseGenre;

    private SubjectCategory subjectCategory;

    public CourseSearchCommand(CourseGenre courseGenre, SubjectCategory subjectCategory) {
        this.courseGenre = courseGenre;
        this.subjectCategory = subjectCategory;

        this.validateSelf();
    }
}
