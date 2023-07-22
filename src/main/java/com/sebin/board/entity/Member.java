package com.sebin.board.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEMBER_ID")
  private Long id;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String nickname;

  private String phoneNumber;

  private String address;

  private String profile;

  @Enumerated(EnumType.STRING)
  private Authority authority;

  public Member() {
  }

  @Builder
  public Member(String email, String password, String nickname, String phoneNumber, String address, String profile, Authority authority) {
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.profile = profile;
    this.authority = authority;
  }


  public void changeNickname(String nickname) {
    this.nickname = nickname;
  }

  public void changePassword(String password) {
    this.password = password;
  }
}
