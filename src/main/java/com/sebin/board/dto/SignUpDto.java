package com.sebin.board.dto;

import com.sebin.board.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
  private String userEmail;
  private String userPassword;
  private String userPasswordCheck;
  private String userNickname;
  private String userPhoneNumber;
  private String userAddress;
  private String userAddressDetail;

  @Builder
  public SignUpDto(String userEmail, String userPassword, String userNickname,
      String userPhoneNumber,
      String userAddress, String userAddressDetail) {
    this.userEmail = userEmail;
    this.userPassword = userPassword;
    this.userNickname = userNickname;
    this.userPhoneNumber = userPhoneNumber;
    this.userAddress = userAddress;
    this.userAddressDetail = userAddressDetail;
  }

  public UserEntity toUserEntity() {
    return UserEntity.builder()
        .userEmail(userEmail)
        .userPassword(userPassword)
        .userNickname(userNickname)
        .userPhoneNumber(userPhoneNumber)
        .userAddress(userAddress + " " + userAddressDetail)
        .build();
  }
}
