package com.shop.service;

import com.shop.dto.*;
import com.shop.entity.Board;
import com.shop.entity.RoadInfo;

import java.util.List;

public interface RoadInfoService {

    List<RoadInfoDto> getCourselist();

    default RoadInfoDto entityToDto(RoadInfo entity) {

        RoadInfoDto dto = RoadInfoDto.builder()
                .courseid(entity.getCourseid())
                .distance(entity.getDistance())
                .leadTime(entity.getLeadTime())
                .relateSubway(entity.getRelateSubway())
                .trafficInfo(entity.getTrafficInfo())
                .detailCourse(entity.getDetailCourse())
                .content(entity.getContent())
                .courseName(entity.getCourseName())
                .codeName(entity.getCodeName())
                .courseLevel(entity.getCourseLevel())
                .build();

        return dto;
    }
}
