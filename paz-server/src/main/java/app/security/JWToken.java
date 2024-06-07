package app.security;

import app.APIConfig;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JWToken {
    public static final String JWT_CALLNAME_CLAIM = "sub";
    public static final String JWT_USERID_CLAIM = "id";
    public static final String JWT_ROLE_CLAIM = "role";
    private static final String JWT_ISSUER_CLAIM = "iss";
    private static final String JWT_IPADDRESS_CLAIM = "ipa";
    private String userName;
    private Long userId;
    private String role;
    private String ipAddress;

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public static String getIpAddress(HttpServletRequest request) {
        // obtain the source IP-address of the current request
        String ipAddress = null;
        ipAddress = request.getHeader(APIConfig.IP_FORWARDED_FOR);
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    public static final String JWT_ATTRIBUTE_NAME = "JWTokenInfo";


    @Value("HvA")
    private String issuer;

    @Value("${jwt.pass-phrase: This is very secret information for my private encryption key. However, this story still is too short for truly secure 512-bit encryption.}")
    private String passphrase;

    @Value("1200") // default 20 minutes
    private int tokenDurationOfValidity;

    public JWToken() {
    }

    public JWToken(String userName, Long userId, String role) {
        this.userName = userName;
        this.userId = userId;
        this.role = role;
    }

    public static JWToken decode(String token, String passphrase)
            throws ExpiredJwtException, MalformedJwtException {
        // Validate the token string and extract the claims

        Key key = getKey(passphrase);
        System.out.println(token);
        Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token);
        System.out.println(jws);
        Claims claims = jws.getBody();

        // build our token from the extracted claims
        JWToken jwToken = new JWToken(
                claims.get(JWT_CALLNAME_CLAIM).toString(),
                Long.valueOf(claims.get(JWT_USERID_CLAIM).toString()),
                claims.get(JWT_ROLE_CLAIM).toString()
        );
        jwToken.setIpAddress((String) claims.get(JWT_IPADDRESS_CLAIM));
        return jwToken;
    }



    public String encode(String issuer, String passphrase, int expiration) {
        Key key = getKey(passphrase);

        return Jwts.builder()
                .claim(JWT_CALLNAME_CLAIM, this.userName)
                .claim(JWT_USERID_CLAIM, this.userId)
                .claim(JWT_ROLE_CLAIM, this.role)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    private static Key getKey(String passPhrase) {
        byte[] hmacKey = passPhrase.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(hmacKey, SignatureAlgorithm.HS512.getJcaName());
    }
}
