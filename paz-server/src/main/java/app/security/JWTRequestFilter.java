package app.security;

import app.APIConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Value("${jwt.passphrase: This is very secret information for my private encryption key. However, this story still is too short for truly secure 512-bit encryption.}")
    private String passphrase;
    @Autowired
    APIConfig apiConfig;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if (HttpMethod.OPTIONS.matches(request.getMethod()) || this.apiConfig.SECURED_PATHS.stream().noneMatch(servletPath::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        String encryptedToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        // block the request if no token was found
        if (encryptedToken == null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No token provided. You need to login first.");
            return;
        }

        //decode the encoded and signed token, after removing optional Bearer prefix
        JWToken jwToken = null;
        try {
            jwToken = JWToken.decode(encryptedToken.replace("Bearer ", ""), passphrase);
        } catch (RuntimeException e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage() + "You need to logon first.");
        }

        // pass-on the token info as an attribute for the request
        request.setAttribute(JWToken.JWT_ATTRIBUTE_NAME, jwToken);
        filterChain.doFilter(request, response);
    }
}
