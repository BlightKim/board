package com.sebin.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "User")
@Table(name = "User")
@Getter
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userNumber;

  @Column(unique = true)
  private String userEmail;

  private String userPassword;

  private String userNickname;

  private String userPhoneNumber;

  private String userAddress;

  private String userProfile;

  public UserEntity() {
  }
  @Builder
  public UserEntity(String userEmail, String userPassword, String userNickname,
      String userPhoneNumber, String userAddress, String userProfile) {
    this.userEmail = userEmail;
    this.userPassword = userPassword;
    this.userNickname = userNickname;
    this.userPhoneNumber = userPhoneNumber;
    this.userAddress = userAddress;
    this.userProfile = userProfile;
  }


}
