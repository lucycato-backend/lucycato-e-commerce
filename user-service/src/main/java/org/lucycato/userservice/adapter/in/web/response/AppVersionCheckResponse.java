package org.lucycato.userservice.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userservice.model.enums.AppVersionCheckStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppVersionCheckResponse {
    private AppVersionCheckStatus appVersionCheckStatus;
}
