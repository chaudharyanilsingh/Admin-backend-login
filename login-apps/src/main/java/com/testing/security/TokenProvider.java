package com.testing.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author orange
 */

@Component
public class TokenProvider implements Serializable {

	private final Log logger = LogFactory.getLog(this.getClass());

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_AUDIENCE = "audience";
	static final String CLAIM_KEY_CREATED = "created";
	static final String LOGIN_TYPE = "type";
	private static final long serialVersionUID = -3301605591108950415L;
	private static final String AUDIENCE_UNKNOWN = "unknown";
	private static final String AUDIENCE_WEB = "web";
	private static final String AUDIENCE_MOBILE = "mobile";
	private static final String AUDIENCE_TABLET = "tablet";

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	private long expirationToken = 5 * 60 * 60;

	private String secret = "secret";

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public String getRoleFromToken(String token) {
		String role;
		try {
			Claims claims = getAllClaimsFromToken(token);
			role = String.valueOf(claims.get("type"));

		} catch (Exception e) {
			role = "";
		}
		return role;

	}

	// retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// generate token for user
	public String generateToken(UserDetails userDetails, String type) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(LOGIN_TYPE, type);
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {

		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
