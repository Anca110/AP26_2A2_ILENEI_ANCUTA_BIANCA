package lab7.advanced.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

//clasa asta stie sa creeze si sa verifice tokenuri JWT
@Component
public class JwtUtil {

    // cheia secreta cu care semnam tokenul
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // cat timp e valabil tokenul: 1 ora
    private final long EXPIRATION = 1000 * 60 * 60;

    // genereaza un token pentru un utilizator
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // cine e utilizatorul
                .setIssuedAt(new Date()) // cand a fost creat
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)) // cand expira
                .signWith(key) // semneaza cu cheia secreta
                .compact();
    }

    // extrage username ul din token primit
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // verifica daca tokenul e valid
    public boolean validateToken(String token) {
        try {
            extractUsername(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

//un token jwt- un sir de genul eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiJ9.xyz123
//format din header-ce algoritm de criptare s a folosit; payload-inform din token(username, cand creat, expirare); semnatura-dovedeste ca e autentic

//User face login → generateToken("admin") → primește tokenul
//User trimite tokenul la /solve → validateToken(token) → dacă e valid îl lasă să treacă