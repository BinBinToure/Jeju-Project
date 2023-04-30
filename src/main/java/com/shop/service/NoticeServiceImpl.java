package com.shop.service;

import com.shop.dto.NoticeDTO;
import com.shop.dto.PageRequestDTO;
import com.shop.dto.PageResultDTO;
import com.shop.entity.Notice;
import com.shop.entity.NoticeImage;
import com.shop.repository.NoticeImageRepository;
import com.shop.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeImageRepository imageRepository;

    @Transactional
    @Override
    public Long register(NoticeDTO noticeDTO) {

        Map<String, Object> entityMap = dtoToEntity(noticeDTO);
        Notice notice = (Notice) entityMap.get("notice");
        List<NoticeImage> noticeImageList = (List<NoticeImage>) entityMap.get("imgList");

        noticeRepository.save(notice);

        noticeImageList.forEach(noticeImage -> {
            imageRepository.save(noticeImage);
        });
        return notice.getMno();
    }

   @Override
    public PageResultDTO<NoticeDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = noticeRepository.getListPage(pageable);

        Function<Object[], NoticeDTO> fn = (arr -> entitiesToDTO(
                (Notice)arr[0] ,
                (List<NoticeImage>)(Arrays.asList((NoticeImage)arr[1])),
                (Double) arr[2],
                (Long)arr[3])
        );

        return new PageResultDTO<>(result, fn);
   }

   @Override
   public NoticeDTO getNotice(Long mno) {

        List<Object[]> result= noticeRepository.getNoticeWithAll(mno);

       Notice notice = (Notice) result.get(0)[0];     // Notice 엔티티는 가장 앞에 존재 - 모든 Row가 동일한 값

       List<NoticeImage> noticeImageList = new ArrayList<>();     // 영화의 이미지 개수만큼 ??Image 객체 필요

       result.forEach(arr -> {
           NoticeImage noticeImage = (NoticeImage)arr[1];
           noticeImageList.add(noticeImage);
       });

       Double avg = (Double) result.get(0)[2];      // 평균 평점 - 모든 Row가 동일한 값
       Long reviewCnt = (Long) result.get(0)[3];    // 리뷰 개수 - 모든 Row가 동일한 값

       return entitiesToDTO(notice, noticeImageList, avg, reviewCnt);
   }
}
