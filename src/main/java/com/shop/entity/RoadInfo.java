package com.shop.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "road_info")
public class RoadInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long courseid;

    private String codeName;
    private String courseName;
    private String courseLevel;
    private String distance;
    private String leadTime;
    private String relateSubway;
    private String trafficInfo;
    private String detailCourse;
    @Column(length = 1000)
    private String content;
}
