package com.shop.repository;

import com.shop.entity.CommentEntity;
import com.shop.entity.Rboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findAllByRboardOrderByIdDesc(Rboard rboard);
}
