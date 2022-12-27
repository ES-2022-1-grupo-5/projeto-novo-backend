package chegamais.com.chagamais.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

import static chegamais.com.chagamais.security.securityConfig.SecurityConstants.*;

@Component
public class JWTAuthorizationFilter {
    public String generateJWTToken(Authentication authentication) {

        String userEmail = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new java.util.Date(System.currentTimeMillis() + EXPIRATION_TIME);

        String token = JWT.create()
                .withIssuer("auth0")
                .withSubject(userEmail)
                .withIssuedAt(currentDate)
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC512(SECRET_KEY.getBytes()));

        return token;
    }
    public String getUserEmailFromToken(String token) {
        String user = JWT.require(Algorithm.HMAC512(SECRET_KEY.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
        return user;
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(SECRET_KEY.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""));
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Token inválido ou incorreto");
        }
    }
}

