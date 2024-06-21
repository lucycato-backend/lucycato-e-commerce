package org.lucycato.productcommandservice.application.port.in;

import org.lucycato.productcommandservice.application.port.in.command.DeleteLectureCommand;
import org.lucycato.productcommandservice.application.port.in.command.ModifyLectureCommand;
import org.lucycato.productcommandservice.application.port.in.command.RegisterLectureCommand;
import org.lucycato.productcommandservice.domain.LectureDetail;

public interface LectureUseCase {

    LectureDetail registerLecture(RegisterLectureCommand command);

    LectureDetail modifyLecture(ModifyLectureCommand command);

    void deleteLecture(DeleteLectureCommand command);
}
