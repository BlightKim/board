package com.sebin.board.jwt;

import static java.util.stream.Collectors.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.boot.test.context.SpringBootTest.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sebin.board.config.RedisConfig;
import com.sebin.board.config.SecurityConfig;
import com.sebin.board.config.code.TestContainerConfig;
import com.sebin.board.controller.AuthController;
import com.sebin.board.dto.MemberInfoDto;
import com.sebin.board.dto.SignInDto;
import com.sebin.board.dto.SignUpDto;
import com.sebin.board.dto.TokenDto;
import com.sebin.board.reposiotry.queryrepository.MemberQueryRepository;
import com.sebin.board.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@SpringBootTest
@Transactional
@ExtendWith(TestContainerConfig.class)
@Slf4j
class TokenProviderTest extends TestContainerConfig {
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 3;

    @Autowired
    private TokenProvider tokenProvider;


    @Autowired
    MemberQueryRepository memberQueryRepository;
    @Test
    @DisplayName("토큰 생성 테스트")
    @WithMockUser
    void generateToken() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TokenDto tokenDto = tokenProvider.generateToken(authentication, new MemberInfoDto());
        Assertions.assertThat(tokenDto)
                .isNotNull()
                .extracting("grantType", "accessToken","tokenExpiresIn", "refreshToken")
                .isNotNull();
    }

    @Test
    void getAuthentication() {
    }

    @Test
    @DisplayName("토큰 검사 테스트")
    @WithMockUser
    void validateToken() {
        // given
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // MockUser 꺼내기
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(joining(","));
        long now = new Date().getTime();
        Date tokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        TokenDto tokenDto = tokenProvider.generateToken(authentication, new MemberInfoDto());
        String refreshToken = tokenProvider.generateRefreshToken(authentication);

        // when

        // that
    }
}