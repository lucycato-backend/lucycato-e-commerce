package org.lucycato.usercoursequeryservice.application.port.in.command;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserCourseSearchCommand extends SelfValidating<UserCourseSearchCommand> {

}
