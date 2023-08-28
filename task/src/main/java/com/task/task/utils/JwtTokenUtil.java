package com.task.task.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

        @Value("${jwt.secret}") // Inject your secret key from properties
        private String secret;

        @Value("${jwt.expiration}") // Inject token expiration time from properties
        private Long expiration;

        // Retrieve username from token
        public String extractUsername(String token) {
            return extractClaim(token, Claims::getSubject);
        }

        // Retrieve expiration date from token
        public Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        }

        // Generic method to retrieve claims from token
        private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        }

        // For retrieving any information from token, we will need the secret key
        private Claims extractAllClaims(String token) {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }

        // Check if the token has expired
        private Boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }

        // Generate token for user
        public String generateToken(UserDetails userDetails) {
            Map<String, Object> claims = new HashMap<>();
            return createToken(claims, userDetails.getUsername());
        }

        // Create token using claims, subject, and expiration
        private String createToken(Map<String, Object> claims, String subject) {
            return Jwts.builder().setClaims(claims).setSubject(subject)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + expiration))
                    .signWith(SignatureAlgorithm.HS256, secret).compact();
        }

        // Validate token
        public Boolean validateToken(String token, UserDetails userDetails) {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }
}

