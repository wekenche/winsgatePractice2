/**
 * 
 */
package fte.api.auth;

import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

/**
 * @author Try-Parser
 *
 */
public class TokenGenerator<T extends Authentication> {
	
	private SignatureAlgorithm algo = SignatureAlgorithm.HS512;
	private static Key secret = MacProvider.generateKey(); 
	private byte[] apiKeySecretBytes = secret.getEncoded(); 
	private Key signingKey = new SecretKeySpec(apiKeySecretBytes, algo.getJcaName()); 
	
	
	public String generateToken(T u) {
	  return Jwts.builder()
			  .setSubject(u.getName())
			  .setIssuer("fujiko")
	          .claim("iss", "http://fujiko")
	          .claim("date", new Date())
	          .claim("auth", u.getAuthorities())
	          .signWith(algo, signingKey)
	          .compact();
	}
	
	
	public Optional<Token> parseToken(String token) {
		try {
		    Jws<Claims> jws = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
		    return Optional.of(Token.token(jws));
		} catch (JwtException | IOException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
}
