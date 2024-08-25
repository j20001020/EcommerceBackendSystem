package com.kai.ecommercebackendsystem.filter;

import com.kai.ecommercebackendsystem.model.User;
import com.kai.ecommercebackendsystem.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtUtil jwtUtil;
    private final List<String> excludedPaths;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public JwtAuthenticationFilter(JwtUtil jwtUtil, @Value("${jwt.excluded-paths}") String excludedPaths, StringRedisTemplate stringRedisTemplate) {
        this.jwtUtil = jwtUtil;
        this.excludedPaths = List.of(excludedPaths.split(","));
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // authenticate and parse jwt token
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String jwt = authHeader.substring(BEARER_PREFIX.length());

        String redisToken = getRedisToken(jwt);

        Claims claims;
        try {
            if (redisToken == null) throw new JwtException("Token validation failed");
            claims = jwtUtil.parseToken(jwt);
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        User user = getUserFromClaims(claims);

        setAuthentication(user);

        filterChain.doFilter(request, response);
    }

    private String getRedisToken(String jwt) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        return operations.get(jwt);
    }

    private void setAuthentication(User user) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));
            Authentication token = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
        }
    }

    private User getUserFromClaims(Claims claims) {
        User user = new User();
        user.setId(Integer.valueOf(claims.getSubject()));
        user.setUsername(claims.get("username", String.class));
        return user;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return excludedPaths.contains(request.getServletPath());
    }
}
