package com.kaushik_phase.kaushik_phase.service;

import io.jsonwebtoken.security.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.*;
import java.security.*;



import java.security.Key;
import java.util.*;

@Component
public class JwtTokenProvider {


    public static String generate1Token() {
        long EXPIRATION_TIME = 864_000_000;

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        Key secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);


        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }
}
