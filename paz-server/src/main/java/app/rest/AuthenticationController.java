package app.rest;

import app.security.JWToken;
import app.exceptions.NotAcceptableException;
import app.models.User;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pazapi/authentication")
public class AuthenticationController {
    long userId = 0;

    @Autowired
    private JWToken tokenGenerator;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody ObjectNode login) throws NotAcceptableException {
        User user;
        String email = login.get("email").asText();
        String password = login.get("password").asText();
        String username = email.split("@")[0];
        String role = "user";

        if(username.equals(password)){
            user = new User(userId, username, email, password, role);

            userId++;

            JWToken tokenGenerator = new JWToken(username, userId, role);

            String tokenString = tokenGenerator.encode("private company", "This is very secret information for my private encryption key. However, this story still is too short for truly secure 512-bit encryption.", 7200);

            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenString)
                    .body(user);
        } else {
            throw new NotAcceptableException("Cannot authenticate user by email=" + email + " and password=" + password);
        }
    }

    }