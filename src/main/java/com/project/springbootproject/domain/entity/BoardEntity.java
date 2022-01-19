package com.project.springbootproject.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*파라미터가 없는 기본 생성자를 추가하는 어노테이션. (JPA 사용을 위해 기본 생성자 생성은 필수)*/
/*access는 생성자의 접근 권한을 설정하는 속성이며,  최종적으로 protected BoardEntity() {}와 동일*/
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
/*객체를 테이블과 매핑할 엔티티라고 JPA에게 알려주는 역할을 하는 어노테이션 (엔티티 매핑)*/
/*@Entity가 붙은 클래스는 JPA가 관리하며, 이를 엔티티 클래스라 함.*/
@Entity
/*엔티티 클래스와 매핑되는 테이블 정보를 명시하는 어노테이션*/
/*name 속성으로 테이블명을 작성할 수 있으며, 생략 시 엔티티 이름이 테이블명으로 자동 매핑*/
@Table(name = "board")
public class BoardEntity extends TimeEntity { /*BoardEntity는 TimeEntity를 상속하고 있음.*/

    /*primaryKey*/
    @Id
    /*기본키로 대체키를 사용할 때, 기본키 값 생성 전략을 명시*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    /*무분별한 setter 사용은 안정성을 보장받기 어려우므로 Builder 패턴을 사용*/
    @Builder
    public BoardEntity(Long id, String title, String content, String writer) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

}


/*
*Entity클래스는 테이블과 관련이 있는 것을 알 수 있음.
*비즈니스 로직은 Entity를 기준으로 돌아가기 때문에 Entity를 Request, Response 용도로 사용하는 것은 적절하지 못함.
*그래서 데이터 전달 목적을 갖는 dto 클래스를 정의하여 사용.
*/
