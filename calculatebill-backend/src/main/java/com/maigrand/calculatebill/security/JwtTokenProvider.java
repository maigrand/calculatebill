package com.maigrand.calculatebill.security;

import com.maigrand.calculatebill.entity.UserEntity;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.security.jwt.secret}")
    private String secret;

    @Value("${app.security.jwt.expire-length}")
    private int expireLength;

    @Value("${app.security.jwt.remember-me-expire-length}")
    private int rememberMeExpireLength;

    public String generateToken(Authentication authentication, boolean rememberMe) {
        UserEntity user = (UserEntity) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate;

        if (rememberMe) {
            expiryDate = new Date(now.getTime() + this.rememberMeExpireLength);
        } else {
            expiryDate = new Date(now.getTime() + this.expireLength);
        }

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }

    //todo: Доделать обработку исключений
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
