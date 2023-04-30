package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {

    private Long mno;
    private String title;

    @Builder.Default
    private List<NoticeImageDTO> imageDTOList = new ArrayList<>();

    // 평균 평점
    private double avg;

    //리뷰 수 jpa의 count( )
    private int reviewCnt;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
