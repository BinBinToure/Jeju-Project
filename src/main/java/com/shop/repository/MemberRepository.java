package com.shop.repository;

import com.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    Member findByEmail(String Email);
    // 회원 가입시 중복 회원이 있는지 검사하기위해 이메일로 회원을 검사할 수 있도록 쿼리 메소드 작성

    Member findByName(String name);

}
