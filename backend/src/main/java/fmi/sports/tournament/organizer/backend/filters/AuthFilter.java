package fmi.sports.tournament.organizer.backend.filters;

import fmi.sports.tournament.organizer.backend.services.JWTService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component
public class AuthFilter extends OncePerRequestFilter {
    private static final int PREFIX_LENGTH = 7;

    private final JWTService jwtService;

    @Autowired
    public AuthFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token == null
                || !token.startsWith("Bearer ")
                || !jwtService.isTokenValid(token.substring(PREFIX_LENGTH))) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().startsWith("/api/auth");
    }
}
