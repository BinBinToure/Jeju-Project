package com.shop.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.shop.dto.*;
import com.shop.entity.Board;
import com.shop.entity.QRoadInfo;
import com.shop.entity.RoadInfo;
import com.shop.repository.RoadInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor    // 의존성 자동 주입
public class RoadInfoServiceImpl implements RoadInfoService {

    private final RoadInfoRepository repository;

    @Override
    @Transactional
    public List<RoadInfoDto> getCourselist() {
        List<RoadInfo> Roads = repository.findAll();
        List<RoadInfoDto> roadInfoDtoList = new ArrayList<>();

        for(RoadInfo roadInfo : Roads) {
            RoadInfoDto roadInfoDto = RoadInfoDto.builder()
                    .courseid(roadInfo.getCourseid())
                    .codeName(roadInfo.getCodeName())
                    .courseName(roadInfo.getCourseName())
                    .courseLevel(roadInfo.getCourseLevel())
                    .distance(roadInfo.getDistance())
                    .leadTime(roadInfo.getLeadTime())
                    .relateSubway(roadInfo.getRelateSubway())
                    .trafficInfo(roadInfo.getTrafficInfo())
                    .detailCourse(roadInfo.getDetailCourse())
                    .content(roadInfo.getContent())
                    .build();

            roadInfoDtoList.add(roadInfoDto);
        }

        return roadInfoDtoList;
    }


}
