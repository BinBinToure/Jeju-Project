package com.shop.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamicInsert
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gno;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

    @Column(name = "view_count", columnDefinition = "integer default 0", nullable = false)
//    @ColumnDefault("0") //default 0
    //columnDefinition = "integer default 0"
    private Long viewCount;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "name")
//    private Member member;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public Board updateViewCount(Long viewCount) {

        this.viewCount = viewCount + 1;
        return this;
    }
}
