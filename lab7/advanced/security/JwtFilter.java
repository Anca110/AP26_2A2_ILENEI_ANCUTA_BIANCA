package lab7.advanced.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// acest filtru intercepteaza FIECARE request care vine la server
// verifica daca are token valid inainte sa il lase sa ajunga la controller
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // lasam /login sa treaca fara token
        String path = request.getRequestURI();
        if (path.equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ia headerul Authorization din request
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (jwtUtil.validateToken(token)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        // nu are token sau e invalid => 401 Unauthorized
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Token lipsa sau invalid");
    }
}