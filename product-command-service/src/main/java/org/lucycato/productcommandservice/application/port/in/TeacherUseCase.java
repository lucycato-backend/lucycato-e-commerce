package org.lucycato.productcommandservice.application.port.in;

import org.lucycato.productcommandservice.application.port.in.command.DeleteTeacherCommand;
import org.lucycato.productcommandservice.application.port.in.command.ModifyTeacherCommand;
import org.lucycato.productcommandservice.application.port.in.command.RegisterTeacherCommand;
import org.lucycato.productcommandservice.domain.TeacherDetail;

public interface TeacherUseCase {

    TeacherDetail registerTeacher(RegisterTeacherCommand command);

    TeacherDetail modifyTeacher(ModifyTeacherCommand command);

    void deleteTeacher(DeleteTeacherCommand command);
}
