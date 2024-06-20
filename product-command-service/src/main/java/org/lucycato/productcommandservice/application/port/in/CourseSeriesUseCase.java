package org.lucycato.productcommandservice.application.port.in;

import org.lucycato.productcommandservice.application.port.in.command.DeleteCourseSeriesCommand;
import org.lucycato.productcommandservice.application.port.in.command.ModifyCourseSeriesCommand;
import org.lucycato.productcommandservice.application.port.in.command.RegisterCourseSeriesCommand;
import org.lucycato.productcommandservice.domain.CourseSeriesDetail;

public interface CourseSeriesUseCase {

    CourseSeriesDetail registerCourseSeries(RegisterCourseSeriesCommand command);

    CourseSeriesDetail modifyCourseSeries(ModifyCourseSeriesCommand command);

    void deleteCourseSeries(DeleteCourseSeriesCommand command);
}
