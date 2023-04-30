package com.shop.dto;

import com.shop.entity.Rboard;
import com.shop.entity.RboardFile;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RboardDTO {

    private Long id;
    private String rboardTitle;
    private String rboardContents;
    private int rboardHits;
    private LocalDateTime regDate, modDate;
    private String createdBy;

    private List<MultipartFile> rboardFile;   // save.html -> Controller로 넘어갈때 파일을 담는 용도
    private List<String> originalFileName;    // 원본 파일 이름
    private List<String> storedFileName;      // 서버 저장용 파일 이름
    private int fileAttached;           // 파일 첨부 여부 (첨부됐을때 1, 미첨부일때 0)

    public RboardDTO(Long id, String rboardTitle, int rboardHits, LocalDateTime regDate, String createdBy) {
        this.id = id;
        this.rboardTitle = rboardTitle;
        this.rboardHits = rboardHits;
        this.regDate = regDate;
        this.createdBy = createdBy;
    }

    public static RboardDTO toRboardDTO(Rboard rboard) {
        RboardDTO rboardDTO = new RboardDTO();
        rboardDTO.setId(rboard.getId());
        rboardDTO.setRboardTitle(rboard.getRboardTitle());
        rboardDTO.setRboardContents(rboard.getRboardContents());
        rboardDTO.setCreatedBy(rboard.getCreatedBy());
        rboardDTO.setRboardHits(rboard.getRboardHits());
        rboardDTO.setRegDate(rboard.getRegTime());
        rboardDTO.setModDate(rboard.getUpdateTime());
        if (rboard.getFileAttached() == 0) {
            rboardDTO.setFileAttached(rboard.getFileAttached()); // 0
        } else {
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            rboardDTO.setFileAttached(rboard.getFileAttached()); // 1
            for (RboardFile rboardFile : rboard.getRboardFileList()) {
                originalFileNameList.add(rboardFile.getOriginalFileName());
                storedFileNameList.add(rboardFile.getStoredFileName());
            }
            rboardDTO.setOriginalFileName(originalFileNameList);
            rboardDTO.setStoredFileName(storedFileNameList);
        }
        return rboardDTO;
    }
}
