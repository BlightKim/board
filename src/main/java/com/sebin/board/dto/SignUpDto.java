package com.sebin.board.dto;

import com.sebin.board.entity.Authority;
import com.sebin.board.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {
    private String email;
    private String password;
    private String passwordCheck;
    private String nickname;
    private String phoneNumber;
    private String address;
    private String addressDetail;

    public Member toMember() {
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .authority(Authority.ROLE_USER)
                .address(address + " " + addressDetail)
                .build();
    }
}
