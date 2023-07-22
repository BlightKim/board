/*
package com.sebin.board.reposiotry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.sebin.board.entity.Member;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

  @Autowired
  MemberRepository memberRepository;
  @Autowired
  EntityManager em;

  @Test
  void existsByUserEmail() {
    Member user = Member
        .builder()
        .email("asd@naver.com")
        .password("12")
        .phoneNumber("010-1234-5678")
        .nickname("rlatpqls")
        .address("포천시")
        .build();

    memberRepository.save(user);

    em.flush();
    em.clear();

    memberRepository.existsByEmail("asd@naver.com");
    assertThat(memberRepository.existsByEmail("asd@naver.com")).isTrue();
  }
}*/
