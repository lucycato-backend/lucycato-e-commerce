package org.lucycato.userauthcommandservice.application.port.in.command;


import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AgreeMarketingTermsCommand extends SelfValidating<AgreeMarketingTermsCommand> {

        @NotNull
        private Long appUserId;

        // 마케팅 약관 동의에 대한 boolean
        @NotNull
        private Boolean isAgreeMarketingTerms;


        public AgreeMarketingTermsCommand(Long appUserId, Boolean isAgreeMarketingTerms) {
            this.appUserId = appUserId;
            this.isAgreeMarketingTerms = isAgreeMarketingTerms;
            validateSelf();
        }

}

