package com.sebin.board.jwt;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sebin.board.config.SecurityConfig;
import com.sebin.board.config.code.TestContainerConfig;
import com.sebin.board.dto.SignInDto;
import com.sebin.board.dto.SignUpDto;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Transactional
class TokenProviderTest extends TestContainerConfig {

  @MockBean
  TokenProvider tokenProvider;

  @Autowired
  private WebApplicationContext context;

  private ObjectMapper objectMapper = new ObjectMapper();

  private MockMvc mvc;


  @BeforeEach
  public void setUp() throws Exception {
    mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(springSecurity())
        .build();
    SignUpDto signUpDto = SignUpDto.builder()
        .email("asd1234@naver.com")
        .password("1234")
        .passwordCheck("1234")
        .address("포천시 ")
        .addressDetail("영북면 운천리")
        .nickname("nickname")
        .phoneNumber("010-1234-5678")
        .build();

    mvc.perform(post("/api/auth/signUp")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsBytes(signUpDto)))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("토큰 생성 테스트")
  void generateToken() throws Exception {
    SignInDto signInDto = new SignInDto();
    signInDto.setEmail("asd1234@naver.com");
    signInDto.setPassword("1234");
    mvc.perform(post("/api/auth/signIn")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsBytes(signInDto))
    ).andExpect(status().isOk());

    mvc.perform(get("/api/board"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void getAuthentication() {
  }

  @Test
  void validateToken() {
  }
}