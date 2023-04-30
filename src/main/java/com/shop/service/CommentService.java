package com.shop.service;

import com.shop.dto.CommentDTO;
import com.shop.entity.CommentEntity;
import com.shop.entity.Rboard;
import com.shop.repository.CommentRepository;
import com.shop.repository.RboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final RboardRepository rboardRepository;

    public Long save(CommentDTO commentDTO) {
        // 부모 엔티티(Rboard) 조회
        Optional<Rboard> optionalRboard = rboardRepository.findById(commentDTO.getRboardId());
        if (optionalRboard.isPresent()) {
            Rboard rboard = optionalRboard.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, rboard);
            return commentRepository.save(commentEntity).getId();
        } else {
            return null;
        }
    }

    public List<CommentDTO> findAll(Long rboardId) {
        Rboard rboard = rboardRepository.findById(rboardId).get();
        List<CommentEntity> commentEntityList =
                commentRepository.findAllByRboardOrderByIdDesc(rboard);
        // EntityList -> DTOList
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, rboardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
