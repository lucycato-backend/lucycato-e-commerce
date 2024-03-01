package org.lucycato.common.exception;

import org.lucycato.common.error.ErrorCode;

public interface ApiException {
    ErrorCode getErrorCode();
    String getReason();
}
