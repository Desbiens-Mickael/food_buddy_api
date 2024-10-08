package fr.olprog_b.food_buddy.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

// @Slf4j
@Service
public class JwtService {

	private final UserDetailsService userDetailsService;
	// @Value("#{${jwt.expiration} * 60 * 1000}")
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Long tokenExpiration;
	private SecretKey key;


	public JwtService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}


	@PostConstruct
	private void init() {
		this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	public Map<String, String> generateToken(String email) {
		UserDetails user = userDetailsService.loadUserByUsername(email);
		return this.generateJwt(user);
	}

	private Map<String, String> generateJwt(UserDetails user) {
		// JWT Dépendance
		// heure creation (millisecondes) date de référence : 01/01/1970
		final long CurrentTime = System.currentTimeMillis();

		final long expirationTime = CurrentTime + (tokenExpiration * 60 * 1000);

		Map<String, Object> claims = Map.of(
				"email", user.getUsername(),
				Claims.EXPIRATION, new Date(expirationTime),
				Claims.SUBJECT, user.getUsername());

		// générer le token avec Jwts
		final String token = Jwts.builder()
				.issuedAt(new Date(CurrentTime))
				.expiration(new Date(expirationTime))
				.subject(user.getUsername())
				.claims(claims)
				.signWith(key)
				.compact();

		return Map.of("bearer", token);

	}

	public String extractUsername(String jwtToken) {
		return getPayLoad(jwtToken).getSubject();
	}

	public boolean isTokenExpired(String jwtToken) {
		Date expiration = new Date();
		return expiration.after(getPayLoad(jwtToken).getExpiration());
	}

	private Claims getPayLoad(String jwtToken) {
		return (Claims) Jwts.parser().verifyWith(key).build().parse(jwtToken).getPayload();
	}
}
