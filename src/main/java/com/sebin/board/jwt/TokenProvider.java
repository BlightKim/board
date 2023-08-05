package com.sebin.board.jwt;

import com.sebin.board.dto.MemberInfoDto;
import com.sebin.board.dto.TokenDto;
import com.sebin.board.service.RedisService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TokenProvider {

  private static final String AUTHORITIES_KEY = "auth";
  private static final String BEARER_TYPE = "bearer";
  private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;
  private final Key key;
  private final RedisService redisService;


  @Autowired
  public TokenProvider(@Value("${jwt.secret}") String secretKey,
      RedisService redisService) {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    this.key = Keys.hmacShaKeyFor(keyBytes);
    this.redisService = redisService;
  }

  /**
   * 토큰 생성
   *
   * @param authentication
   * @param memberInfoDto
   * @return tokenDto
   */
  public TokenDto generateToken(Authentication authentication, MemberInfoDto memberInfoDto) {
    String authorities = authentication
        .getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining((",")));

    long now = (new Date()).getTime();

    Date tokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);

    log.info("tokenExpiresIn={}", tokenExpiresIn);

    // Access Token을 생성한다.
    String accessToken = generateAccessToken(authentication, authorities, tokenExpiresIn);

    // Refresh Token을 생성한다.
    String refreshToken = generateRefreshToken(authentication);

    return TokenDto.builder()
        .grantType(BEARER_TYPE)
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .tokenExpiresIn(tokenExpiresIn.getTime())
        .member(memberInfoDto)
        .build();
  }

  private String generateRefreshToken(Authentication authentication) {
    Claims claims = Jwts.claims().setSubject(authentication.getName());
    Date now = new Date();
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + 1000 * 60 * 60 * 24 * 3)) // Refresh Token 유효시간(3일)
        .signWith(SignatureAlgorithm.HS512, key) // HS512 알고리즘으로 key를 암호화
        .compact();
  }


  public Authentication getAuthentication(String accessToken) {
    Claims claims = parseClaims(accessToken);

    if (claims.get(AUTHORITIES_KEY) == null) {
      throw new RuntimeException("권한정보가 없는 토큰입니다.");
    }

    Collection<? extends GrantedAuthority> authorities = Arrays.stream(
            claims.get(AUTHORITIES_KEY).toString().split(","))
        .map(SimpleGrantedAuthority::new)
        .toList();

    UserDetails principal = new User(claims.getSubject(), "", authorities);

    return new UsernamePasswordAuthenticationToken(principal, "", authorities);
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
      return true;
    } catch (SecurityException | MalformedJwtException e) {
      log.info("잘못된 JWT 서명입니다.");
    } catch (ExpiredJwtException e) {
      log.info("만료된 JWT 토큰입니다.");
      throw e;
    } catch (UnsupportedJwtException e) {
      log.info("지원하지 않는 JWT 토큰입니다.");
    } catch (IllegalArgumentException e) {
      log.info("JWT 토큰이 잘못되었습니다.");
    }
    return false;
  }

  private Claims parseClaims(String accessToken) {
    try {
      return Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(accessToken)
          .getBody();
    } catch (ExpiredJwtException e) {
      return e.getClaims();
    }
  }

  private String generateAccessToken(Authentication authentication, String authorities,
      Date tokenExpiresIn) {
    return Jwts.builder()
        .setSubject(authentication.getName())
        .claim(AUTHORITIES_KEY, authorities)
        .setExpiration(tokenExpiresIn)
        .signWith(key, SignatureAlgorithm.HS512)
        .compact();
  }
}
