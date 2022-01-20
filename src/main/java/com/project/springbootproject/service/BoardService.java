package com.project.springbootproject.service;

import com.project.springbootproject.domain.entity.BoardEntity;
import com.project.springbootproject.domain.repository.BoardRepository;
import com.project.springbootproject.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*생성자로 Bean 객체를 받는 방식을 해결해주는 어노테이션*/
/*Repository를 주입하기 위해 사용*/
@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Transactional
    public List<BoardDto> getBoardList() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        /*Controller와 Service간에 데이터 전달은 dto 객체로 하기 위해, Repository에서 가져온 Entity를 반복문을 통해 dto로 변환하는 작업*/
        for (BoardEntity boardEntity : boardEntities) {
            BoardDto boardDto = BoardDto.builder()
                    .id(boardEntity.getId())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .writer(boardEntity.getWriter())
                    .createdDate(boardEntity.getCreateDate())
                    .build();

            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    /*선언적 트랜잭션이라 부르며, 트랜잭션을 적용하는 어노테이션*/
    @Transactional
    public Long savePost(BoardDto boardDto) {
        /* save()
        JpaRepository에 정의된 메서드로, DB에 INSERT, UPDATE를 담당한다
        매개변수로는 Entity를 전달*/
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public BoardDto getPost(Long id) {
        /*findById() : PK 값을 where 조건으로 하여, 데이터를 가져오기 위한 메서드, JpaRepository 인터페이스에서 정의되어 있음*/
        /*반환 값은 Optional 타입인데, 엔티티를 쏙 빼오려면 boardEntityWrapper.get(); 이렇게 get() 메서드를 사용해서 가져오면 됨.*/
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        BoardDto boardDto = BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreateDate())
                .build();

        return boardDto;
    }

    @Transactional
    public void deletePost(Long id) {
        /*deleteById() : PK값을 where 조건으로 하여, 데이터를 삭제하기 위한 메서드이며, JpaRepository 인터페이스에서 정의되어 있음.*/
        boardRepository.deleteById(id);
    }
}
