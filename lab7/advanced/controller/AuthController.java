package lab7.advanced.controller;

import lab7.advanced.security.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

// endpoint ul de login
// primeste username si parola, daca sunt corecte returneaza un token JWT
@RestController
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username,
                                        @RequestParam String password) {

        // verificam username ul si parola
        // pentru simplitate le hardcodam aici
        if (username.equals("admin") && password.equals("admin123")) {
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Username sau parola gresita");
    }
}