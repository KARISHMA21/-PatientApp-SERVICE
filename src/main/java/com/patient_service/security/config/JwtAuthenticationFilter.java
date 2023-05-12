package com.patient_service.security.config;

import com.patient_service.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private static final String ONE_HOUR = "3600";


    @Value("${admin.client.id}")
    private String adminClientId;
    @Override
    protected void doFilterInternal(
          @NonNull HttpServletRequest request,
          @NonNull  HttpServletResponse response,
          @NonNull  FilterChain filterChain) throws ServletException, IOException {

            final String authHeader=request.getHeader("Authorization");
            final String jwt;
            final  String username;
            if(authHeader==null||!authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request,response);
                return;
            }

        if (authHeader != null && !"".equals(authHeader) && authHeader.startsWith("Bearer ")) {
            String subject = jwtService.extractID(authHeader);
            System.out.println("subject :" + subject);
            if (adminClientId.equals(subject) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken ut = new UsernamePasswordAuthenticationToken(adminClientId, null, null);
                ut.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(ut);
            }
//        }
            else {
                jwt = authHeader.substring(7);
                //extract username from token
                username = jwtService.extractUsername(jwt);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                    if (jwtService.isTokenValid(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                        authenticationToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }

                }
            }
        }

        response.setHeader("Access-Control-Allow-Origin", "*");

        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", ONE_HOUR);
        response.setHeader("Access-Control-Request-Headers", "authorization,content-type");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested- With,Origin,Content-Type, Accept, x-device-user-agent, Content-Type");
        filterChain.doFilter(request,response);

    }

}
