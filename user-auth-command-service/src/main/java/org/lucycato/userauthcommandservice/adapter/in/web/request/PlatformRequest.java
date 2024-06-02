package org.lucycato.userauthcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userauthcommandservice.domain.enums.PlatformType;
import org.lucycato.userauthcommandservice.domain.enums.NetworkType;

@Getter
@NoArgsConstructor
public class PlatformRequest {
    private PlatformType platformType;

    private String platformVersion;

    private NetworkType networkType;

    private String locale;
}
