package org.lucycato.common.exception;

import org.lucycato.common.api.Result;
import org.lucycato.common.error.ErrorCode;

public interface ApiException {
    Integer getHttpCode();
    Result getResult();
}
