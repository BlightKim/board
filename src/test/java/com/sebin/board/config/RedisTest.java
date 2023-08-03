package com.sebin.board.config;



import com.sebin.board.config.code.TestContainerConfig;
import com.sebin.board.service.RedisService;
import com.sebin.board.service.RedisServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(RedisConfig.class)
@ExtendWith(TestContainerConfig.class)
class RedisTest {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  private RedisService redisService;

  @BeforeEach
  public void beforeEach() {
    redisService = new RedisServiceImpl(redisTemplate);
  }

  @Test
  public void test() {
    redisService.setData("세빈", "수진", 1000 * 60 * 600L);
    String value = redisService.getDate("세빈");
    Assertions.assertThat(value).isEqualTo("수진");
  }
}