package com.shop.controller;

import com.shop.dto.BlogInfoDTO;
import com.shop.dto.JejuDto;
import com.shop.service.BlogService;
import com.shop.service.JejuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor // 자동 주입을 위한 Annotation
@RequestMapping("/where")
public class JejuController {

    private final JejuService service;
    private final BlogService blogService;

    @GetMapping("/list")
    public String list() {

        return "where/list";
    }

    @GetMapping("/stay1")
    public String stay1(Model model) {
        List<JejuDto> jejuDtoList = service.getJejuList();
        model.addAttribute("jejuList", service.getJejuList());

        return "where/stay1";
    }
    @GetMapping("/stay2")
    public String stay2(Model model) {
        List<JejuDto> jejuDtoList = service.getJejuList();
        model.addAttribute("jejuList", service.getJejuList());

        return "where/stay2";
    }
    @GetMapping("/stay3")
    public String stay3(Model model) {
        List<JejuDto> jejuDtoList = service.getJejuList();
        model.addAttribute("jejuList", service.getJejuList());

        return "where/stay3";
    }
    @GetMapping("/stay4")
    public String stay4(Model model) {
        List<JejuDto> jejuDtoList = service.getJejuList();
        model.addAttribute("jejuList", service.getJejuList());

        return "where/stay4";
    }
    @GetMapping("/stay5")
    public String stay5(Model model) {
        List<JejuDto> jejuDtoList = service.getJejuList();
        model.addAttribute("jejuList", service.getJejuList());
        List<BlogInfoDTO> blogInfoDTOList = blogService.getBlogList();
        model.addAttribute("blogList", blogService.getBlogList());

        return "where/stay5";
    }
    @GetMapping("/stay6")
    public String stay6(Model model) {
        List<JejuDto> jejuDtoList = service.getJejuList();
        model.addAttribute("jejuList", service.getJejuList());

        return "where/stay6";
    }


}
