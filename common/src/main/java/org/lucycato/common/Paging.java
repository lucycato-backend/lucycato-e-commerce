package org.lucycato.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.BinaryOperator;

@Getter
@NoArgsConstructor
public class Paging<T> {
    private T body;

    private Integer page;

    private Integer size;

    private Boolean isLast;

    public static <T> Paging<T> CREATE(T body, Integer page, Integer size, Boolean isLast) {
        Paging<T> paging = new Paging<>();
        paging.body = body;
        paging.page = page;
        paging.size = size;
        paging.isLast = isLast;

        return paging;
    }
}
