package com.shop.entity;

import com.shop.dto.RboardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Rboard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String rboardTitle;

    @Column(length = 500)
    private String rboardContents;

    @Column
    private int rboardHits;

    @Column
    private int fileAttached; // 0 or 1

    @OneToMany(mappedBy = "rboard", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RboardFile> rboardFileList = new ArrayList<>();

    @OneToMany(mappedBy = "rboard", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static Rboard toSaveEntity(RboardDTO rboardDTO) {
        Rboard rboard = new Rboard();
        rboard.setRboardTitle(rboardDTO.getRboardTitle());
        rboard.setRboardContents(rboardDTO.getRboardContents());
        rboard.setRboardHits(0);
        rboard.setFileAttached(0);  // 파일 없음.
        return rboard;
    }

    public static Rboard toUpdateEntity(RboardDTO rboardDTO) {
        Rboard rboard = new Rboard();
        rboard.setId(rboardDTO.getId());            // 아이디로 새글인지 수정인지 구분
        rboard.setRboardTitle(rboardDTO.getRboardTitle());
        rboard.setRboardContents(rboardDTO.getRboardContents());
        rboard.setRboardHits(rboardDTO.getRboardHits());
        return rboard;
    }

    public static Rboard toSaveFileEntity(RboardDTO rboardDTO) {
        Rboard rboard = new Rboard();
        rboard.setRboardTitle(rboardDTO.getRboardTitle());
        rboard.setRboardContents(rboardDTO.getRboardContents());
        rboard.setRboardHits(0);
        rboard.setFileAttached(1);  // 파일 있음.
        return rboard;
    }
}
