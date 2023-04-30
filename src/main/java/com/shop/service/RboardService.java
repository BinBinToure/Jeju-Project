package com.shop.service;

import com.shop.dto.BoardDTO;
import com.shop.dto.RboardDTO;
import com.shop.entity.Rboard;
import com.shop.entity.RboardFile;
import com.shop.repository.RboardFileRepisotry;
import com.shop.repository.RboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// DTO -> Entity    (Entity Class)
// Entity -> DTO    (DTO Class)


@Service
@RequiredArgsConstructor
public class RboardService {

    private final RboardRepository rboardRepository;
    private final RboardFileRepisotry rboardFileRepisotry;

    public void save(RboardDTO rboardDTO) throws IOException {
        // 파일 첨부 여부에 따라 로직 분리
        if (rboardDTO.getRboardFile().isEmpty()) {
            // 첨부 파일 없으면
            Rboard rboard = Rboard.toSaveEntity(rboardDTO);
            rboardRepository.save(rboard);
        } else {
            // 첨부 파일 있으면
            /*
                1. DTO에 담긴 파일을 꺼냄
                2. 파일의 이름 가져옴
                3. 서버 저장용 이름을 만듦
                4. 저장 경로 설정
                5. 해당 경로에 파일 저장
                6. rboard 테이블에 해당 데이터 save 처리
                7. rboard_file_table에 해당 데이터 save 처리
             */
            Rboard rboard = Rboard.toSaveFileEntity(rboardDTO);
            Long savedId = rboardRepository.save(rboard).getId();
            Rboard board = rboardRepository.findById(savedId).get();
            for (MultipartFile rboardFile : rboardDTO.getRboardFile()) {

                String originalFileName = rboardFile.getOriginalFilename();                     // 2
                String storedFileName = System.currentTimeMillis() + "_" + originalFileName;    // 3
                String savePath = "C:/upload/" + storedFileName;                                // 4
                // String savePath = "/Users/사용자이름/upload/" + storedFileNAme; 맥
                rboardFile.transferTo(new File(savePath));                                      // 5
                RboardFile rboardFileEntity = RboardFile.toRboardFile(board, originalFileName, storedFileName);
                rboardFileRepisotry.save(rboardFileEntity);
                // 변수명 주의
            }
        }

    }

    @Transactional
    public List<RboardDTO> findAll() {
        List<Rboard> rboardList = rboardRepository.findAll();
        List<RboardDTO> rboardDTOList = new ArrayList<>();
        for (Rboard rboard : rboardList) {
            rboardDTOList.add(RboardDTO.toRboardDTO(rboard));
        }

        return rboardDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        rboardRepository.updateHits(id);
    }

    @Transactional
    public RboardDTO findById(Long id) {
        Optional<Rboard> optionalRboard = rboardRepository.findById(id);
        if(optionalRboard.isPresent()) {
            Rboard rboard = optionalRboard.get();
            RboardDTO rboardDTO = RboardDTO.toRboardDTO(rboard);
            return rboardDTO;
        } else {
            return null;
        }
    }

    public RboardDTO update(RboardDTO rboardDTO) {
        Rboard rboard = Rboard.toUpdateEntity(rboardDTO);
        rboardRepository.save(rboard);
        return findById(rboardDTO.getId());
    }

    public void delete(Long id) {
        rboardRepository.deleteById(id);
    }

    public Page<RboardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() -1;
        int pageLimit = 10;      // 한 페이지에 보여줄 글 갯수
        // 한페이지당 x개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<Rboard> rboards =
                rboardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        // 목록 : id, 작성자, 제목, 조회수, 작성 시간
        Page<RboardDTO> rboardDTOS = rboards.map(rboard -> new RboardDTO(rboard.getId(), rboard.getRboardTitle(), rboard.getRboardHits(), rboard.getRegTime(), rboard.getCreatedBy()));
        return rboardDTOS;
    }
}
