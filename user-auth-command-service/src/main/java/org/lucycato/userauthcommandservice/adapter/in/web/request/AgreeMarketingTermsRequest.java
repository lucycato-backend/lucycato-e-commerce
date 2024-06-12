package org.lucycato.userauthcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AgreeMarketingTermsRequest {
    private Long appUserId;

    private Boolean isAgreeMarketingTerms;
}
