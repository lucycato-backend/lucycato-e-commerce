package org.lucycato.userservice.adapter.out.persistence.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.lucycato.userservice.adapter.out.persistence.entity.AdminUserJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.lucycato.userservice.adapter.out.persistence.entity.QAdminUserJpaEntity.adminUserJpaEntity;

@Repository
@RequiredArgsConstructor
public class QAdminUserRepository {
    private final JPAQueryFactory queryFactory;

    public Optional<AdminUserJpaEntity> findOneByEmailAndPassword(String email, String password) {
        AdminUserJpaEntity result = queryFactory.selectFrom(adminUserJpaEntity)
                .where(adminUserJpaEntity.email.eq(email),
                        adminUserJpaEntity.password.eq(password))
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
