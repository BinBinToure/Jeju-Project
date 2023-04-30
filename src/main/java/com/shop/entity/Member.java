package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter @Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    private String email;
    private String name;

    private String password;
    private String phoneNm;
    private String postCode;
    private String roadAdd;
    private String jibunAdd;
    private String detailAdd;
    private String extraAdd;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Member 엔티티 생성 메소드
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {

        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setPhoneNm(memberFormDto.getPhoneNm());
        member.setPostCode(memberFormDto.getPostCode());
        member.setRoadAdd(memberFormDto.getRoadAdd());
        member.setJibunAdd(memberFormDto.getJibunAdd());
        member.setDetailAdd(memberFormDto.getDetailAdd());
        member.setExtraAdd(memberFormDto.getExtraAdd());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);

        return member;
    }

    @Builder
    public Member(String name, String email, Role role, String phoneNm, String postCode,
                  String roadAdd, String jibunAdd, String detailAdd, String extraAdd) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.phoneNm = phoneNm;
        this.postCode = postCode;
        this.roadAdd = roadAdd;
        this.jibunAdd = jibunAdd;
        this.detailAdd = detailAdd;
        this.extraAdd = extraAdd;
    }

}
