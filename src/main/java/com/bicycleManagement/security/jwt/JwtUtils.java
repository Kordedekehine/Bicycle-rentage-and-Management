package com.bicycleManagement.security.jwt;

import com.bicycleManagement.security.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.net.Authenticator;
import java.util.Date;

@Component
public class JwtUtils {

    //ALL WE WANT TO ACHIEVE WITH THIS CLASS
//    generate a JWT from username, date, expiration, secret
//    get username from JWT
//    validate a JWT

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${korede.app.jwtSecret}")
    private String jwtSecret;

    @Value("${korede.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.ES512,jwtSecret).compact();
    }
}
