package back.Infrustructure.TokenManager;

// import io.jsonwebtoken.Jwts;

// import javax.crypto.SecretKey;
// import javax.crypto.spec.SecretKeySpec;

// import java.util.HashMap;
// import java.util.Map;

// interface ITokenManagerFactory {
//     public ITokenManager createInstance();
// }

// public class TMFactory implements ITokenManagerFactory {
//     @Override
//     public ITokenManager createInstance() {
//         return new TokenManager();
//     }
// }

// class TokenManager implements ITokenManager {
//     public static final byte[] secretBytes = "TESTTESTTESTTESTTESTTESTETTSTSTETSETET".getBytes();
//     public static final SecretKey secretKey = new SecretKeySpec(secretBytes, 0, secretBytes.length, "HmacSHA256");

//     @Override
//     public String generateToken(String username, String passwd) {
//         return Jwts.builder().subject(username).claim("passwd", passwd).signWith(secretKey).compact();
//     }

//     @Override
//     public Map<String, String> getTokenInfo(String token) {
//         String username = (String) Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("sub");
//         String passwd = (String) Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("passwd");

//         Map<String, String> map = new HashMap<>();
//         map.put("username", username);
//         map.put("passwd", passwd);

//         return map;
//     }
// }

public class TMFactory {
    private static ITokenManager instance = null;

    public static ITokenManager createTokenManager() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }
}
