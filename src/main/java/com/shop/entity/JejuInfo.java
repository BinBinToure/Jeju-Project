package com.shop.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JejuInfo {

    @Id
    private Long id;
    private String sortation;
    private String tradeName;
    private String location;
    private double latitude;
    private double longitude;



}
