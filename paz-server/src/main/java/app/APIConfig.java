package app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Set;

@Configuration
//@EnableWebMvc
public class APIConfig implements WebMvcConfigurer {

    public static final String IP_FORWARDED_FOR = "X-Forwarded-For";
    // a variable reboot signature can be used as an additional security layer in authentication tokens.
    private static final double REBOOT_CODE = 63.0427;

    // JWT configuration that can be adjusted from application.properties
    @Value("${jwt.issuer:private company}")
    public  String issuer;
    @Value("${jwt.passphrase:This is very secret information for my private encryption key. However, this story still is too short for truly secure 512-bit encryption.}")
    public String passphrase;
    @Value("${jwt.duration-of-validity:1200}")
    public int tokenDurationOfValidity;

    // path prefixes that will be protected by the authentication filter
    public Set<String> SECURED_PATHS = Set.of("/pazapi/cabins");

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:*", getHostIPAddressPattern())
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE, IP_FORWARDED_FOR)
                .exposedHeaders(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE, IP_FORWARDED_FOR)
                .allowCredentials(true)
        ;
    }

    private String getHostIPAddressPattern() {
        try {
            return "http://" + Inet4Address.getLocalHost().getHostAddress() + ":*";
        } catch (UnknownHostException ignored) {
        }
        return "http://192.168.*.*:*";
    }

    public String getIssuer() {
        return String.format("%s-%f", this.issuer, REBOOT_CODE);
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public int getTokenDurationOfValidity() {
        return tokenDurationOfValidity;
    }

    public void setTokenDurationOfValidity(int tokenDurationOfValidity) {
        this.tokenDurationOfValidity = tokenDurationOfValidity;
    }
}
