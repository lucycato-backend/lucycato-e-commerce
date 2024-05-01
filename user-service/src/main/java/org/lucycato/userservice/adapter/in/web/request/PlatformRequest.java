package org.lucycato.userservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userservice.domain.enums.PlatformType;
import org.lucycato.userservice.domain.enums.NetworkType;

@Getter
@NoArgsConstructor
public class PlatformRequest {
    private PlatformType platformType;

    private String platformVersion;

    private NetworkType networkType;

    private String locale;
}
