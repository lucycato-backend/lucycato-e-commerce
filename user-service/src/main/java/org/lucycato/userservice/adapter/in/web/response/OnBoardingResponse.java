package org.lucycato.userservice.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnBoardingResponse {
    private Long onBoardingId;

    private String imageUrl;

    private String title;

    private String content;
}
