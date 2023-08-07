package com.sebin.board.service;

import io.jsonwebtoken.ExpiredJwtException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void setData(String key, String value, Long expiredTime) {
        // 데이터 저장
        redisTemplate.opsForValue().set(key,value,expiredTime, TimeUnit.MICROSECONDS);
    }

    @Override
    public String getData(String key) {
        // 데이터 셀렉트 --> 반환은 스트링
        String result = (String) redisTemplate.opsForValue().get(key);

        if (result == null) {
            throw new ExpiredJwtException(null, null, "토큰이 만료되었습니다.");
        }
        return result;
    }

    @Override
    public void deleteData(String key) {
        redisTemplate.delete(key);   
    }
}
