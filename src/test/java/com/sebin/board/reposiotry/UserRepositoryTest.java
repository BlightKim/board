package com.sebin.board.reposiotry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.sebin.board.entity.UserEntity;
import com.sebin.board.entity.UserEntity.UserEntityBuilder;
import com.sebin.board.exception.DuplicateIdException;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;
  @Autowired
  EntityManager em;

  @Test
  void existsByUserEmail() {
    UserEntity user = UserEntity
        .builder()
        .userEmail("asd@naver.com")
        .userPassword("12")
        .userPhoneNumber("010-1234-5678")
        .userNickname("rlatpqls")
        .userAddress("포천시")
        .build();

    userRepository.save(user);

    em.flush();
    em.clear();

    userRepository.existsByUserEmail("asd@naver.com");
    assertThat(userRepository.existsByUserEmail("asd@naver.com")).isTrue();
  }
}