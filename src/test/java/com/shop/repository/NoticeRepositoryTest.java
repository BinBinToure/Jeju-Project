package com.shop.repository;

import com.shop.entity.Notice;
import com.shop.entity.NoticeImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NoticeRepositoryTest {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private NoticeImageRepository imageRepository;

    @Commit
    @Transactional
    @Test
    public void insertNotice() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            Notice notice = Notice.builder().title("Notice...." + i).build();

            System.out.println("-------------------------------------------");

            noticeRepository.save(notice);

            int count = (int)(Math.random() * 5) + 1;           // 1, 2, 3, 4

            for(int j=0; j<count; j++) {
                NoticeImage noticeImage = NoticeImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .notice(notice)
                        .imgName("test" + j + ".jpg").build();

                imageRepository.save(noticeImage);
            }

            System.out.println("==============================================");
        });
    }

    @Test
    public void testListPage() {

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));

        Page<Object[]> result = noticeRepository.getListPage(pageRequest);

        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }

//    @Test
//    public void testMovieWithAll() {
//
//        List<Object[]> result = movieRepository.getMovieWithAll(20L);
//
//        System.out.println(result);
//
//        for (Object[] arr : result) {
//            System.out.println(Arrays.toString(arr));
//        }
//    }


}