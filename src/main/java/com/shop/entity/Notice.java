package com.shop.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor     // 모든 필드 값을 파라미터로 받는 생성자를 만듦
@NoArgsConstructor      // 파라미터가 없는 기본 생성자를 생성
@Getter
@ToString
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // 기본 키 매핑, 2개 다 쓰면 자동 생성
    private Long mno;

    private String title;
}
