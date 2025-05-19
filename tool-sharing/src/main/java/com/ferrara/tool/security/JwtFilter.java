package com.ferrara.tool.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

//@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /*
     As we designed our application, this service will be executed every
     time a request is sent
    */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        /*
          we don't need to execute the code for api/v1/auth
        */
        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        //final String authHeader = request.getHeader("Authorization");
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String jwt;
        final String userEmail; //we'll extract from the TOKEN
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            /*
                if the token is not valid or as expected, go out of this method
            */
            return;
        }
        // "Bearer " counts 7 spaces
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        /*
            check if the user is already authenticated or not
            SecurityContextHolder.getContext().getAuthentication() == null means not
        */
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //fetch the user from the database
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                /*
                    UsernamePasswordAuthenticationToken is used by Spring Security later on
                    in order to check and update the SecurityContextHolder
                */
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

