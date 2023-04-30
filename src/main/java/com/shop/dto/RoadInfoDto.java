package com.shop.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoadInfoDto {

    private Long courseid;
    private String codeName;
    private String courseName;
    private String courseLevel;
    private String distance;
    private String leadTime;
    private String relateSubway;
    private String trafficInfo;
    private String detailCourse;
    private String content;

}
