package com.shop.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Jeju {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
