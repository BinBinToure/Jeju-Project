package com.shop.controller;

import com.shop.dto.BoardDTO;
import com.shop.dto.PageRequestDTO;
import com.shop.entity.Board;
import com.shop.repository.BoardRepository;
import com.shop.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor // 자동 주입을 위한 Annotation
public class BoardController {

    private final BoardService service;


    @GetMapping("/list")
    public void list(Long gno, PageRequestDTO pageRequestDTO, Model model){

        log.info("list............." + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));

    }

    @GetMapping("/register")
    public void register() {
        log.info("register get...");
    }

    @PostMapping("/register")
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto..." + dto);

        // 새로 추가된 엔티티의 번호
        Long gno = service.register(dto);
        redirectAttributes.addFlashAttribute("msg", gno);
        return "redirect:/board/list";
    }

/*    @GetMapping("/read")
    public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("gno : " + gno);

        GuestbookDTO dto = service.read(gno);

        model.addAttribute("dto", dto);
    }*/

    @GetMapping({"/read", "/modify"})
    public void read(Long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("gno : " + gno);
        BoardDTO dto = service.read(gno);
        model.addAttribute("dto", dto);

        Board board = service.selectBoardDetail(gno);

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setGno(gno);
        boardDTO.setTitle(board.getTitle());
        boardDTO.setContent(board.getContent());
        model.addAttribute("boardDTO", boardDTO);
    }



    @PostMapping("/remove") // 삭제
    public String remove(long gno, RedirectAttributes redirectAttributes) {

        log.info("gno : " + gno);
        service.remove(gno);
        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/board/list";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes) {

        log.info("post modify............................................");
        log.info("dto : " + dto);

        service.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("gno", dto.getGno());

        return "redirect:/board/read";
    }
}
