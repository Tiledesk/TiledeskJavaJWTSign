package jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TiledeskJWT {

    
    public static String createJWT(
    		String id,
    		String subject,
    		String aud,
    		String SECRET_KEY,
    		String firstname,
    		String lastname,
    		String email) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        
        byte[] apiKeySecretBytes;
        apiKeySecretBytes = SECRET_KEY.getBytes();
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(subject)
                .claim("aud", aud)
                .claim("_id", id)
                .claim("firstname", firstname)
                .claim("lastname", lastname)
                .claim("email", email)
                .signWith(signingKey, signatureAlgorithm);

        return builder.compact();
    }

}
