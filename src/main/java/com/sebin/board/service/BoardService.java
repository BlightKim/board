package com.sebin.board.service;

import com.sebin.board.dto.BoardDto;
import com.sebin.board.dto.PopularSearchDto;
import com.sebin.board.dto.ResponseDto;
import java.util.List;

public interface BoardService {
  public ResponseDto<List<BoardDto>> searchTopThree();
  public ResponseDto<List<BoardDto>> searchList();
  public ResponseDto<List<PopularSearchDto>> searchPopularSearchList();
}
