package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogInfoDTO {

    private Long id;

    private String title;
    private String contents;
    private String alink;
    private String blink;
    private String address;
}
