package com.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "rboard_file_table")
public class RboardFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rboard_id")
    private Rboard rboard;

    public static RboardFile toRboardFile(Rboard rboard, String originalFileName, String storedFileName) {
        RboardFile rboardFile = new RboardFile();
        rboardFile.setOriginalFileName(originalFileName);
        rboardFile.setStoredFileName(storedFileName);
        rboardFile.setRboard(rboard);
        return rboardFile;
    }
}
