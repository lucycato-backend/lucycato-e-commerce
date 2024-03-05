package org.lucycato.common.error;

public interface ErrorCode {
    Integer getHttpCode();
    String getCode();
    String getReason();
    String getFrontMessage();
}
