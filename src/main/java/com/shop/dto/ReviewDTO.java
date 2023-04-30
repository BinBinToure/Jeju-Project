package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    //review num
    private Long reviewnum;

    private Long mno;

    //Member id
    private Long mid;
    //Member email
    private String email;
    private String name;

    private int grade;

    private String text;

    private LocalDateTime regDate, modDate;


}
