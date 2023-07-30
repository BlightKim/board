package com.sebin.board.config;

import static org.junit.jupiter.api.Assertions.*;

import com.sebin.board.config.code.AbstractContainerBaseTest;
import com.sebin.board.service.RedisService;
import com.sebin.board.service.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisTest extends AbstractContainerBaseTest {
  @Autowired
  private RedisTemplate<String, Object> redisTemplate;
  @Autowired
  private RedisService redisService = new RedisServiceImpl(redisTemplate)

}