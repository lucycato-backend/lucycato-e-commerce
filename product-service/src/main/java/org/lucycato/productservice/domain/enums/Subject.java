package org.lucycato.productservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Subject {
    SPRING("Spring", ProductGenre.DEVELOPER),
    DJANGO("Django", ProductGenre.DEVELOPER),
    NODE_EXPRESS("Express.js", ProductGenre.DEVELOPER),
    NODE_REST("REST.js", ProductGenre.DEVELOPER),
    REACT_JS("React.js", ProductGenre.DEVELOPER),
    NEXT_JS("Next.js", ProductGenre.DEVELOPER),
    IOS("iOS", ProductGenre.DEVELOPER),
    ANDROID("Android", ProductGenre.DEVELOPER),
    FLUTTER("Flutter", ProductGenre.DEVELOPER),
    REACT_NATIVE("React Native", ProductGenre.DEVELOPER),
    VISUAL_DESIGN("시각 디자인", ProductGenre.DESIGN),
    SPACE_DESIGN("공간 디자인", ProductGenre.DESIGN),
    MARKETING("마케팅", ProductGenre.BUSINESS),
    MIND_SET("마인드 셋", ProductGenre.BUSINESS),
    ;
    private final String description;

    private final ProductGenre productGenre;

}
