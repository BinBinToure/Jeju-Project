package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JejuDto {

    private int id;

    private String name;
    private String area;
    private String flag;

    private String reviewAdd;   // 리뷰 링크
    private String address;     // 홈페이지 주소

    private String rating;

    private double latitude;
    private double longitude;

}
