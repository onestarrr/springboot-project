package com.project.springbootproject.dto;

import com.project.springbootproject.domain.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    /*toEntity()
    dto에서 필요한 부분을 빌더 패턴을 통해 entity로 만듦
    필요한 Entity는 이런 식으로 추가하면 됨.*/
    public BoardEntity toEntity() {
        BoardEntity boardEntity = BoardEntity.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .build();
            return boardEntity;
    }

    /*dto는 Controller <> Service <> Repository 간에 필요한 데이터를 캡슐화한 데이터 전달 객체*/
    @Builder
    public BoardDto(Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}
