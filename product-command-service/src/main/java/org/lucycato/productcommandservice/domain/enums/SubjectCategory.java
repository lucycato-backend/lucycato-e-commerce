package org.lucycato.productcommandservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SubjectCategory {
    SPRING("Spring", TeachingGenre.DEVELOPER),
    DJANGO("Django", TeachingGenre.DEVELOPER),
    NODE_EXPRESS("Express.js", TeachingGenre.DEVELOPER),
    NODE_REST("REST.js", TeachingGenre.DEVELOPER),
    REACT_JS("React.js", TeachingGenre.DEVELOPER),
    NEXT_JS("Next.js", TeachingGenre.DEVELOPER),
    IOS("iOS", TeachingGenre.DEVELOPER),
    ANDROID("Android", TeachingGenre.DEVELOPER),
    FLUTTER("Flutter", TeachingGenre.DEVELOPER),
    REACT_NATIVE("React Native", TeachingGenre.DEVELOPER),
    VISUAL_DESIGN("시각 디자인", TeachingGenre.DESIGN),
    SPACE_DESIGN("공간 디자인", TeachingGenre.DESIGN),
    MARKETING("마케팅", TeachingGenre.BUSINESS),
    MIND_SET("마인드 셋", TeachingGenre.BUSINESS),
    ;
    private final String description;

    private final TeachingGenre teachingGenre;

}
