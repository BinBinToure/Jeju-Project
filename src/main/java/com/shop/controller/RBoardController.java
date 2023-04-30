package com.shop.controller;

import com.shop.dto.BoardDTO;
import com.shop.dto.CommentDTO;
import com.shop.dto.RboardDTO;
import com.shop.service.CommentService;
import com.shop.service.RboardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rboard")
public class RBoardController {

    private final RboardService rboardService;
    private final CommentService commentService;

    @GetMapping("/board")
    public String index() {
        return "rboard/board";
    }

    @GetMapping("/save")
    public String saveForm() {
        return "rboard/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute RboardDTO rboardDTO) throws IOException {
        System.out.println("rboradDTO = " + rboardDTO);
        rboardService.save(rboardDTO);

        return "redirect:/rboard/paging";
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<RboardDTO> rboardDTOList = rboardService.findAll();
        model.addAttribute("rboardList", rboardDTOList);
        return "rboard/list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model,
                           @PageableDefault(page=1) Pageable pageable) {

        // 해당 게시글의 조회수를 하나 올리고 게시글 데이터를 가져와서 detail.html에 출력

        rboardService.updateHits(id);
        RboardDTO rboardDTO = rboardService.findById(id);
        /* 댓글 목록 가져오기 */
        List<CommentDTO> commentDTOList = commentService.findAll(id);
        model.addAttribute("commentList", commentDTOList);

        model.addAttribute("rboard", rboardDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "rboard/detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        RboardDTO rboardDTO = rboardService.findById(id);
        model.addAttribute("rboardUpdate", rboardDTO);
        return "rboard/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute RboardDTO rboardDTO, Model model) {
        RboardDTO rboard = rboardService.update(rboardDTO);
        model.addAttribute("rboard", rboard);
        return "rboard/detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        rboardService.delete(id);
        return "redirect:/rboard/paging";
    }

    // /rboard/paging?page=1
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
    //    pageable.getPageNumber();
        Page<RboardDTO> rboardList = rboardService.paging(pageable);
        int blockLimit = 3; // 밑에 보여지는 페이지 갯수
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < rboardList.getTotalPages()) ? startPage + blockLimit - 1 : rboardList.getTotalPages();

        model.addAttribute("rboardList", rboardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/rboard/paging";
    }
}
