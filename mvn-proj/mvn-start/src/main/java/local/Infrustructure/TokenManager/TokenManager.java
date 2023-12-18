package Infrustructure.TokenManager;

import app.ITokenManager;
import io.jsonwebtoken.Jwts;
import jakarta.enterprise.context.ApplicationScoped;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

public class TokenManager implements ITokenManager {
    public static final byte[] secretBytes = "secretKey".getBytes();
    public static final SecretKey secretKey = new SecretKeySpec(secretBytes, "HmackSHA256");

    @Override
    public String generateToken(String username, String passwd) {
        return Jwts.builder()
                .subject(username)
                .claim("passwd", passwd)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public Map<String, String> getTokenInfo(String token) {
        String username = (String) Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("sub");
        String passwd = (String) Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("passwd");

        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("passwd", passwd);

        return map;
    }
}
