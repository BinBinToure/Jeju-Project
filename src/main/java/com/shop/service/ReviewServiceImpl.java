package com.shop.service;

import com.shop.dto.ReviewDTO;
import com.shop.entity.Notice;
import com.shop.entity.Review;
import com.shop.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;


    @Override
    public List<ReviewDTO> getListOfNotice(Long mno){

        Notice notice = Notice.builder().mno(mno).build();

        List<Review> result = reviewRepository.findByNotice(notice);

        return result.stream().map(noticeReview -> entityToDto(noticeReview)).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO noticeReviewDTO) {

        Review noticeReview = dtoToEntity(noticeReviewDTO);

        reviewRepository.save(noticeReview);

        return noticeReview.getReviewnum();
    }

    @Override
    public void modify(ReviewDTO noticeReviewDTO) {

        Optional<Review> result =
                reviewRepository.findById(noticeReviewDTO.getReviewnum());

        if(result.isPresent()){

            Review noticeReview = result.get();
            noticeReview.changeGrade(noticeReviewDTO.getGrade());
            noticeReview.changeText(noticeReviewDTO.getText());

            reviewRepository.save(noticeReview);
        }

    }

    @Override
    public void remove(Long reviewnum) {

        reviewRepository.deleteById(reviewnum);

    }
}
