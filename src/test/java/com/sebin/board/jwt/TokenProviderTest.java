package com.sebin.board.jwt;

import static org.junit.jupiter.api.Assertions.*;

import com.sebin.board.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = AutowireMode.ALL)
@ActiveProfiles(profiles = "local")
class TokenProviderTest {

  @LocalServerPort
  private int port;

  private final TestRestTemplate testRestTemplate;
  private final RedisService redisService;

  public TokenProviderTest(TestRestTemplate testRestTemplate, RedisService redisService) {
    this.testRestTemplate = testRestTemplate;
    this.redisService = redisService;
  }

  @BeforeEach
  void beforeEach() {

  }
  @Test
  @DisplayName("토큰 생성 테스트")
  void generateToken() {
    String clientId = ""
  }

  @Test
  void getAuthentication() {
  }

  @Test
  void validateToken() {
  }
}