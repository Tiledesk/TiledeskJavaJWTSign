# Tiledesk Java JWT Generator for Custom Authentication

This project uses [JJWT library](https://github.com/jwtk/jjwt) to sign a *JSON Web Token* (JWT) to use in Java projects for [Tiledesk Custom Authentication](https://developer.tiledesk.com/apis/authentication).

This example project demonstrate how to create and sign a JWT with Java language for strong authentication of your organization's users on Tiledesk. 

Import this project as a **Maven project**.

You can find the simple logic in class is TiledeskJWT.java

```
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
    		String issuer,
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
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .claim("firstname", firstname)
                .claim("lastname", lastname)
                .claim("email", email)
                .signWith(signingKey, signatureAlgorithm);

        return builder.compact();
    }

}
```

The executable class is Sign.java. This class just demostrate how to sign and get a token for Tiledesk Custom Authentican using the provided TiledeskJWT.java class.

```
package jwt;

public class Sign {

	public static void main(String[] args) {
		
		// Tiledesk data
		String secret = "YOUR-TILEDESK-PROJECT-SECRET-KEY"; // You Tiledesk project secret
		String projectId = "YOUR-TILEDESK-PROJECT-ID"; // Your Tiledesk projectId
		// Organization data
		String OrgUserId = "OrgUserId"; // Organizazion unique User id
		String OrgUserFirstname = "User Firstname"; // OPT
		String OrgUserLastname = "User Lastname"; // OPT
		String OrgUserEmail = "User Email"; // OPT
		// JWT data
		String issuer = "My Organization";
		String tiledeskUserId = projectId + "_" + OrgUserId;
		String subject = "userexternal";
		String aud = "https://tiledesk.com/projects/" + projectId;
		String jwt = TiledeskJWT.createJWT(
				tiledeskUserId,
				issuer,
				subject,
				aud,
				secret,
				OrgUserFirstname,
				OrgUserLastname,
				OrgUserEmail);
		System.out.println("JWT " + jwt);
	}

}
```

