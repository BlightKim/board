package com.sebin.board.reposiotry;

import com.sebin.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
  public boolean existsByEmail(String email);
  public Optional<Member> findByEmail(String email);
}
