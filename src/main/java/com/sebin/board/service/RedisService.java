package com.sebin.board.service;

public interface RedisService {
    public void setData(String key, String value, Long expiredTime); // 데이터를 저장한다

    public String getData(String key); // 데이터를 조회한다

    public void deleteData(String key); // 데이터를 삭제한다.
}
