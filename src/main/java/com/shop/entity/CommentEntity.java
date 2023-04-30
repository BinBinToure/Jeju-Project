package com.shop.entity;

import com.shop.dto.CommentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String commentWriter;

    @Column
    private String commentContents;

    /* Rboard : Comment 1:N */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rboard_id")
    private Rboard rboard;

    public static CommentEntity toSaveEntity(CommentDTO commentDTO, Rboard rboard) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setRboard(rboard);
        return commentEntity;
    }
}
