package org.lucycato.productcommandservice.application.port.in;

import org.lucycato.productcommandservice.application.port.in.command.DeleteCourseCommand;
import org.lucycato.productcommandservice.application.port.in.command.ModifyCourseCommand;
import org.lucycato.productcommandservice.application.port.in.command.RegisterCourseCommand;
import org.lucycato.productcommandservice.domain.CourseDetail;

public interface CourseUseCase {

    CourseDetail registerCourse(RegisterCourseCommand command);

    CourseDetail modifyCourse(ModifyCourseCommand command);

    void deleteCourse(DeleteCourseCommand command);
}
