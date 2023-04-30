package com.shop.service;


import com.shop.dto.BoardDTO;
import com.shop.dto.PageRequestDTO;
import com.shop.dto.PageResultDTO;
import com.shop.entity.Board;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardService {

    Long register(BoardDTO dto);

    BoardDTO read(Long gno);

    PageResultDTO<BoardDTO, Board> getList(PageRequestDTO requestDTO);

    default Board dtoToEntity(BoardDTO dto) {
        Board entity = Board.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
        return entity;
    }

    default BoardDTO entityToDto(Board entity) {

        BoardDTO dto = BoardDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .createdBy(entity.getCreatedBy())
                .regDate(entity.getRegTime())
                .modDate(entity.getUpdateTime())
                .viewCount(entity.getViewCount())
                .build();

        return dto;
    }

    void remove(Long gno);

    void modify(BoardDTO dto);

    Board selectBoardDetail(Long gno);

}
