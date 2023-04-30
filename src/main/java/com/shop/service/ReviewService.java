package com.shop.service;


import com.shop.dto.ReviewDTO;
import com.shop.entity.Member;
import com.shop.entity.Notice;
import com.shop.entity.Review;

import java.util.List;

public interface ReviewService {

    //의 모든 리뷰를 가져온다.
    List<ReviewDTO> getListOfNotice(Long mno);

    // 리뷰를 추가
    Long register(ReviewDTO noticeReviewDTO);

    // 특정한 리뷰 수정
    void modify(ReviewDTO noticeReviewDTO);

    // 리뷰 삭제
    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDTO noticeReviewDTO){

        Review noticeReview = Review.builder()
                .reviewnum(noticeReviewDTO.getReviewnum())
                .notice(Notice.builder().mno(noticeReviewDTO.getMno()).build())
                .member(Member.builder().email(noticeReviewDTO.getEmail()).build())
                .grade(noticeReviewDTO.getGrade())
                .text(noticeReviewDTO.getText())
                .build();

        return noticeReview;
    }

    default ReviewDTO entityToDto(Review noticeReview){

        ReviewDTO noticeReviewDTO = ReviewDTO.builder()
                .reviewnum(noticeReview.getReviewnum())
                .mno(noticeReview.getNotice().getMno())
                .email(noticeReview.getMember().getEmail())
                .name(noticeReview.getMember().getName())
                .grade(noticeReview.getGrade())
                .text(noticeReview.getText())
                .regDate(noticeReview.getRegTime())
                .modDate(noticeReview.getUpdateTime())
                .build();

        return noticeReviewDTO;
    }
}
