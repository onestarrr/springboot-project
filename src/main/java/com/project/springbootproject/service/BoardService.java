package com.project.springbootproject.service;

import com.project.springbootproject.domain.repository.BoardRepository;
import com.project.springbootproject.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/*생성자로 Bean 객체를 받는 방식을 해결해주는 어노테이션*/
/*Repository를 주입하기 위해 사용*/
@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;

    /*선언적 트랜잭션이라 부르며, 트랜잭션을 적용하는 어노테이션*/
    @Transactional
    public Long savePost(BoardDto boardDto) {
        /* save()
        JpaRepository에 정의된 메서드로, DB에 INSERT, UPDATE를 담당한다
        매개변수로는 Entity를 전달*/
        return boardRepository.save(boardDto.toEntity()).getId();
    }

}
