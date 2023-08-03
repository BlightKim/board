package com.sebin.board.service;

import com.sebin.board.dto.BoardDto;
import com.sebin.board.reposiotry.queryrepository.BoardQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{
  private final BoardQueryRepository boardQueryRepository;
  @Override
  public List<BoardDto> searchTopThree() {
    boardQueryRepository.findTopThree();
  }
}
