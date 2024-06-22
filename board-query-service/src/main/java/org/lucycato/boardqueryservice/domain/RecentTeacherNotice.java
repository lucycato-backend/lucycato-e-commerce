package org.lucycato.boardqueryservice.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RecentTeacherNotice {

    private boolean isExist;

    private List<Long> noticeIds;

    public static RecentTeacherNotice from() {
        return RecentTeacherNotice.builder().build();
    }

}
