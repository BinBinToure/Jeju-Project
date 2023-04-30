package com.shop.service;

import com.shop.dto.JejuDto;
import com.shop.entity.Jeju;
import com.shop.repository.JejuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor    // 의존성 자동 주입
public class JejuServiceImpl implements JejuService {

    private final JejuRepository repository;

    @Override
    @Transactional
    public List<JejuDto> getJejuList() {
        List<Jeju> jejus = repository.findAll();
        List<JejuDto> jejuDtoList = new ArrayList<>();

        for(Jeju jeju : jejus) {
            JejuDto jejuDto = JejuDto.builder()
                    .id(jeju.getId())
                    .name(jeju.getName())
                    .latitude(jeju.getLatitude())
                    .longitude(jeju.getLongitude())
                    .area(jeju.getArea())
                    .flag(jeju.getFlag())
                    .reviewAdd(jeju.getReviewAdd())
                    .address(jeju.getAddress())
                    .rating(jeju.getRating())
                    .build();

            jejuDtoList.add(jejuDto);
        }

        return jejuDtoList;
    }
}
