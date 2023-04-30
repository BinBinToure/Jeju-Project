package com.shop.repository;


import com.shop.entity.Board;
import com.shop.entity.RoadInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface RoadInfoRepository extends JpaRepository<RoadInfo, Long>, QuerydslPredicateExecutor<RoadInfo> {
}
