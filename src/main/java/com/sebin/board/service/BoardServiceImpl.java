package com.sebin.board.service;

import com.sebin.board.dto.BoardDto;
import com.sebin.board.dto.PopularSearchDto;
import com.sebin.board.dto.ResponseDto;
import com.sebin.board.reposiotry.BoardRepository;
import com.sebin.board.reposiotry.PopularSearchRepository;
import com.sebin.board.reposiotry.queryrepository.BoardQueryRepository;
import com.sebin.board.reposiotry.queryrepository.PopularSearchQueryRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService{
  private final BoardQueryRepository boardQueryRepository;
  private final BoardRepository boardRepository;
  private final PopularSearchRepository popularSearchRepository;
  private final PopularSearchQueryRepository popularSearchQueryRepository;

  @Override
  public ResponseDto<List<BoardDto>> searchTopThree() {
    LocalDate now = LocalDate.now();
    LocalDate oneWeekAgo = now.minusWeeks(1);
    List<BoardDto> boardDtoList = boardQueryRepository.findTopThree(now, oneWeekAgo);
    return ResponseDto.setSuccess("Success",boardDtoList);
  }

  @Override
  public ResponseDto<List<BoardDto>> searchList() {
    List<BoardDto> boardDtoList = boardQueryRepository.findList();
    return ResponseDto.setSuccess("Success", boardDtoList);
  }

  @Override
  public ResponseDto<List<PopularSearchDto>> searchPopularSearchList() {
    List<PopularSearchDto> popularSearchList = popularSearchQueryRepository.findPopularSearchList();
    return ResponseDto.setSuccess("Success", popularSearchList);
  }
}
