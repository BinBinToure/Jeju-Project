package com.shop.service;

import com.shop.dto.BlogInfoDTO;
import com.shop.dto.JejuDto;
import com.shop.entity.BlogInfo;
import com.shop.entity.Jeju;
import com.shop.repository.BlogInfoRepository;
import com.shop.repository.JejuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor    // 의존성 자동 주입
public class BlogServiceImpl implements BlogService {

    private final BlogInfoRepository repository;

    @Override
    @Transactional
    public List<BlogInfoDTO> getBlogList() {

        List<BlogInfo> blogInfos = repository.findAll();
        List<BlogInfoDTO> blogInfoDTOList = new ArrayList<>();

        for(BlogInfo blogInfo : blogInfos) {
            BlogInfoDTO blogInfoDTO = BlogInfoDTO.builder()
                    .id(blogInfo.getId())
                    .title(blogInfo.getTitle())
                    .contents(blogInfo.getContents())
                    .alink(blogInfo.getAlink())
                    .blink(blogInfo.getBlink())
                    .address(blogInfo.getAddress())
                    .build();

            blogInfoDTOList.add(blogInfoDTO);
        }

        return blogInfoDTOList;
    }
}
