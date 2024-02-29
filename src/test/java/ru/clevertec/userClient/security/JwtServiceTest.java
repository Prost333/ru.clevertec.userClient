package ru.clevertec.userClient.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {
    String key="c92cef2481ba7a58dc63485105e6c0bc289670ba31108ff8cdf6402ae92dc84e";
    @Test
    public void testGenerateToken() {
        JwtService jwtService = new JwtService();
        UserDetails userDetails = mock(UserDetails.class);
        ReflectionTestUtils.setField(jwtService, "secret", key);
        ReflectionTestUtils.setField(jwtService, "expirationTime", 3600000);

        String token = jwtService.generateToken(userDetails);

        assertTrue(token.length() > 0);
    }

    @Test
    public void testExtractUserName() {
        JwtService jwtService = new JwtService();
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("testUser");
        ReflectionTestUtils.setField(jwtService, "secret", key);
        ReflectionTestUtils.setField(jwtService, "expirationTime", 3600000);

        String token = jwtService.generateToken(userDetails);
        String username = jwtService.extractUserName(token);

        assertEquals("testUser", username);
    }

    @Test
    public void testIsTokenValid() {
        JwtService jwtService = new JwtService();
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("testUser");
        ReflectionTestUtils.setField(jwtService, "secret", key);
        ReflectionTestUtils.setField(jwtService, "expirationTime", 3600000);

        String token = jwtService.generateToken(userDetails);
        boolean isValid = jwtService.isTokenValid(token, userDetails);

        assertTrue(isValid);
    }
}