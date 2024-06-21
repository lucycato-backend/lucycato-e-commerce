package org.lucycato.ordercommandservice.application.port.out;

public interface AdminUserPort {
    boolean hasAdminRights(Long adminUserId) throws Exception;
}
