package com.sebin.board.controller;

import com.sebin.board.config.SecurityConfig;
import com.sebin.board.dto.SignUpDto;
import com.sebin.board.jwt.JwtAuthenticationEntryPoint;
import com.sebin.board.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SecurityConfig.class)
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
class AuthControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private JwtA

    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    @WithAnonymousUser
    public void login_url() {

    }
    @Test
    @DisplayName("비밀번호가 틀리면 Exception 발생")
    public void signUpTest() throws Exception {
        // 회원가입을 위한 정보를 보낸다
        SignUpDto signUpDto = new SignUpDto().builder()
                .email("asd123@naver.com")
                .password("1234")
                .passwordCheck("12345")
                .nickname("sujin")
                .address("포천시")
                .addressDetail("영북면 운천리")
                .phoneNumber("010-1234-5678")
                .build();

        // signUpDto를 json으로 변환
        String signUpDtoJson = objectMapper.writeValueAsString(signUpDto);
        mvc.perform(
                MockMvcRequestBuilders.post("/api/auth/signUp")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(signUpDtoJson)
        ).andExpect(status().isOk());

    }
}