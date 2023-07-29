package com.sebin.board.service;

public interface RedisService {
    public void setData(String key, String value, Long expiredTime);

    public String getDate(String key);

    public void deleteData(String key);
}
