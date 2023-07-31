package com.abdulchakam.loginservice.util.jwt;

import com.abdulchakam.loginservice.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private static final long JWT_EXPIRATION_MS = 900000;
    private static final String JWT_SECRET = "mySecretKey";

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS)) // 15 minutes
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.getSubject();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, String authenticatedUsername) {
        final String usernameFromToken = getUsernameFromToken(token);
        final boolean equalsUsername = usernameFromToken.equals(authenticatedUsername);
        final boolean tokenExpired = isTokenExpired(token);

        return equalsUsername && !tokenExpired;
    }

    private boolean isTokenExpired(String token) {

        final Date expirationDateFromToken = getExpirationDateFromToken(token);
        return expirationDateFromToken.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {

        final Claims claims = getAllClaimsFromToken(token);
        return claims.getExpiration();
    }
}
