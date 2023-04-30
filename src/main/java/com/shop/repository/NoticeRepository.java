package com.shop.repository;


import com.shop.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {


//    @Query("select m, avg(coalesce(r.grade,0)),  count(r) from Movie m " +
//            "left outer join Review  r on r.movie = m group by m")
//    Page<Object[]> getListPage(Pageable pageable);

    @Query("select n, ni, avg(coalesce(r.grade,0)),  count(distinct r) from Notice n " +
            "left outer join NoticeImage ni on ni.notice = n " +
            "left outer join Review  r on r.notice = n group by n ")
    Page<Object[]> getListPage(Pageable pageable);


    @Query("select n, ni ,avg(coalesce(r.grade,0)),  count(r)" +
            " from Notice n left outer join NoticeImage ni on ni.notice = n " +
            " left outer join Review  r on r.notice = n "+
            " where n.mno = :mno group by ni")
    List<Object[]> getNoticeWithAll(@Param("mno") Long mno);              // 특정  조회
}
