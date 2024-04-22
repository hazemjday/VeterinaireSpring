package com.example.nonAuth.config;



import com.example.nonAuth.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;




    @Service
    public class JwtService {



        public String generateToken(User user, Map<String, Object> extraClaims) {
            Date issuedAt = new Date(System.currentTimeMillis());
            Date expiration = new Date(issuedAt.getTime() + (7 * 24 * 60 * 60 * 1000));
            return Jwts.builder()
                    .setClaims(extraClaims)
                    .setSubject(user.getUsername())
                    .setIssuedAt(issuedAt)
                    .setExpiration(expiration)
                    .signWith(generateKey(), SignatureAlgorithm.HS256)
                    .compact();
        }


        private Key generateKey(){
            byte[] secreateAsBytes = Decoders.BASE64.decode("dGhpcyBpcyBteSBzZWN1cmUga2V5IGFuZCB5b3UgY2Fubm90IGhhY2sgaXQ=");
            return Keys.hmacShaKeyFor(secreateAsBytes);
        }

        public String extractUsername(String jwt) {
            return extractAllClaims(jwt).getSubject();
        }

        private Claims extractAllClaims(String jwt) {
            return Jwts.parserBuilder().setSigningKey(generateKey()).build()
                    .parseClaimsJws(jwt).getBody();
        }


    }
