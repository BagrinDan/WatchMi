package com.example.Calendar.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class TokenFilter extends OncePerRequestFilter {
    private final JwtCore jwtcore;
    private final UserDetailsService userDetailsService;

    public TokenFilter(JwtCore jwtCore, UserDetailsService userDetailsService){
        this.jwtcore = jwtCore;
        this.userDetailsService = userDetailsService;
    }
    /*
    * Данный класс извлекает Bearer JWT токен из запросов юзера,
    * чтобы проверить если он вошел в систему
    * */
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain)
                                    throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization"); // Перехватываем запрос с Authorization
        final String jwt;
        final String username;

        if(authHeader == null || !authHeader.startsWith("Bearer ")){ // Извлекаем токен полсе "Bearer `"
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        username = jwtcore.getNameFromJwt(jwt);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if(jwtcore.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}

// Deprecated. Will be deleted soon
//String jwt = null;
//String username = null;
//UserDetails userDetails;
//UsernamePasswordAuthenticationToken auth;
//
//        try{
//
//                if(headerAuth != null && headerAuth.startsWith("Bearer ")){
//jwt = headerAuth.substring(7);
//            }
//                    if(jwt != null){
//        try {
//username = jwtcore.getNameFromJwt(jwt);
//                } catch (ExpiredJwtException e){
//        throw new IOException(e);
//                }
//                        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//userDetails = userDetailsService.loadUserByUsername(username);
//auth = new UsernamePasswordAuthenticationToken(
//        userDetails,
//                            null);
//                    SecurityContextHolder.getContext().setAuthentication(auth);
//                }
//                        }
//
//                        } catch (Exception e){
//        throw new IOException(e);
//        }
//                filterChain.doFilter(request, response);