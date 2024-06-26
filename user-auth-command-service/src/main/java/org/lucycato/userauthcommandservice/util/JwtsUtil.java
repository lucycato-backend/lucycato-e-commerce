package org.lucycato.userauthcommandservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtsUtil {

    private static final String SECRET_KEY = "your-secret-key";
    private static final String issuer = "lucycato";
    private static final long expirationMinutes = 5;
    private static final long refreshExpirationMinutes = 30;

    private static final String Admin = "Admin#";

    private static final String App = "App#";

    public static String createAdminJwtToken(String currentDeviceMacAddress) {
        return Admin + Jwts.builder()
                .setSubject(currentDeviceMacAddress)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setIssuer(issuer)
                .setExpiration(Date.from(Instant.now().plus(expirationMinutes, ChronoUnit.MINUTES)))
                .compact();
    }

    public static String createAppJwtToken(String currentDeviceMacAddress) {
        return App + Jwts.builder()
                .setSubject(currentDeviceMacAddress)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setIssuer(issuer)
                .setExpiration(Date.from(Instant.now().plus(expirationMinutes, ChronoUnit.MINUTES)))
                .compact();
    }

    public static String createAdminRefreshToken() {
        return Admin + Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setIssuer(issuer)
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setExpiration(Date.from(Instant.now().plus(refreshExpirationMinutes, ChronoUnit.MINUTES)))
                .compact();
    }

    public static String createAppRefreshToken() {
        return App + Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setIssuer(issuer)
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setExpiration(Date.from(Instant.now().plus(refreshExpirationMinutes, ChronoUnit.MINUTES)))
                .compact();
    }

}
