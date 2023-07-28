package com.sebin.board.service;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl {
  private final StringRedisTemplate stringRedisTemplate;

  public void setStringOps(String key, String value, long ttl, TimeUnit timeUnit) {
    stringRedisTemplate.opsForValue().set(key, value, ttl, timeUnit);
  }

  public String getStringOps(String key) {
    return (String)stringRedisTemplate.opsForValue().get(key);
  }

}
