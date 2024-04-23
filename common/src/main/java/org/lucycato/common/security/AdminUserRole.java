package org.lucycato.common.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum AdminUserRole {
    MASTER(0, "마스터 권한"),
    MANAGER(1, "사이트 관리자 권한"),
    TEACHER(2, "강사 권한"),
    RESEARCHER(3, "선생님 연구소 직원 권한"),
    TEACHING_ASSISTANT(4, "조교 권한"),
    NONE(5, "권한 없음"),
    ;
    private final Integer priority;
    private final String description;

    public boolean checkHeightPriority(List<AdminUserRole> targetRoles) {
        for (AdminUserRole role : targetRoles) {
            if (this.priority <= role.priority) {
                return true;
            }
        }
        return false;
    }

    public boolean checkRole(List<AdminUserRole> targetRoles) {
        return targetRoles.contains(this);
    }
}
