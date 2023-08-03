package com.sebin.board.service;

import com.sebin.board.dto.BoardDto;
import java.util.List;

public interface BoardService {
  public List<BoardDto> searchTopThree();
}
