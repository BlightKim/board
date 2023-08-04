package com.sebin.board.controller;

import com.sebin.board.config.SecurityUtil;
import com.sebin.board.dto.BoardDto;
import com.sebin.board.dto.MemberInfoDto;
import com.sebin.board.dto.PopularSearchDto;
import com.sebin.board.dto.ResponseDto;
import com.sebin.board.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
  private final BoardService boardService;

  @GetMapping("/")
  public String getBoard(@AuthenticationPrincipal User user) {
    String email = SecurityUtil.getCurrentMemberEmail();
    return "getBoard";
  }

  @GetMapping("/top3")
  public ResponseDto<List<BoardDto>> getTopThree() {
    return boardService.searchTopThree();
  }

  @GetMapping("/list")
  public ResponseDto<List<BoardDto>> getList() {
    return boardService.searchList();
  }

  @GetMapping("/popularSearchList")
  public ResponseDto<List<PopularSearchDto>> getPopularSearchList() {
    return boardService.searchPopularSearchList();
  }
}


