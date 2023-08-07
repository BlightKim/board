package com.sebin.board.jwt;

import static com.mysema.commons.lang.Assert.assertThat;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.sebin.board.config.code.TestContainerConfig;
import com.sebin.board.dto.MemberInfoDto;
import com.sebin.board.dto.TokenDto;
import com.sebin.board.reposiotry.queryrepository.MemberQueryRepository;
import com.sebin.board.service.RedisService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@SpringBootTest
@Transactional
@ExtendWith(TestContainerConfig.class)
@Slf4j
class TokenProviderTest extends TestContainerConfig {

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 3; // 토큰 만료 테스트를 위한 시간 설정
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 3; // 토큰 만료 테스트를 위한 시간 설정
    @Autowired
    RedisService redisService;
    @Autowired
    MemberQueryRepository memberQueryRepository;
    @Autowired
    private TokenProvider tokenProvider;

    @Test
    @DisplayName("토큰 생성 테스트")
    @WithMockUser
    void generateToken() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TokenDto tokenDto = tokenProvider.generateToken(authentication, new MemberInfoDto());
        Assertions.assertThat(tokenDto)
                .isNotNull()
                .extracting("grantType", "accessToken", "tokenExpiresIn", "refreshToken")
                .isNotNull();
    }

    @Test
    void getAuthentication() {
    }

    @Test
    @DisplayName("access 토큰 검사 테스트")
    @WithMockUser
    void validateAccessToken() throws InterruptedException {
        // given
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication(); // MockUser 꺼내기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(joining(","));
        long now = new Date().getTime();
        Date tokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = tokenProvider.generateAccessToken(authentication, authorities,
                tokenExpiresIn);

        // when
        Thread.sleep(4000); // 4초 정지
        // that
        assertThatThrownBy(() -> tokenProvider.validateToken(accessToken)).isInstanceOf(
                ExpiredJwtException.class);
        //
    }

    @Test
    @DisplayName("refresh 토큰 검사 테스트")
    @WithMockUser
    void validateRefreshToken() throws InterruptedException {
        // given
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication(); // MockUser 꺼내기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(joining(","));
        long now = new Date().getTime();
        Date refreshTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String refreshToken = tokenProvider.generateRefreshToken(authentication, REFRESH_TOKEN_EXPIRE_TIME);
        redisService.setData(authentication.getName(), refreshToken,
                refreshTokenExpiresIn.getTime()); // redis에 저장 만료시간은 3초로 설정

        // when
        String findRefreshToken = redisService.getData(authentication.getName());

        // that
        Assertions.assertThat(findRefreshToken).isEqualTo(refreshToken);
    }

    @Test
    @DisplayName("refresh 토큰 만료 시 JwtExpiredException 던짐")
    @WithMockUser
    void throwJwtExpiredExceptionByExpirationOfRefreshToken() throws InterruptedException {
        // given
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication(); // MockUser 꺼내기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(joining(","));
        long now = new Date().getTime();
        Date refreshTokenExpiresIn = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);
        String refreshToken = tokenProvider.generateRefreshToken(authentication, REFRESH_TOKEN_EXPIRE_TIME);
        redisService.setData(authentication.getName(), refreshToken,
                1000 * 1L); // redis에 저장 만료시간은 3초로 설정

        // when
        Thread.sleep(5000); // 5초 기다린다
        ; // redis에서 조회한다.

        // that
        assertThatThrownBy(() -> redisService.getData(
                authentication.getName()))
                .isInstanceOf(ExpiredJwtException.class);// 만료시간이 경과되었으므로 redis에서 조회되지 않고 Exception 발생
    }
}