package com.njp.study.springboot.domain.posts;

import com.njp.study.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/** Domain Model **/

// Posts 클래스는 실제 DB의 테이블과 매칭될 클래스 (=Entity 클래스)
// Setter가 없는 이유:
// 무작정 생성하는 경우, 해당 클래스이 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확하게 구분할 수가 없다.
// 때문에 Entity 클래스에서는 절대 Setter 메소드를 만드지 않는다.
// 대신, 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야한다.

// 클래스 내 모든 필드의 Getter 메소드를 자동새성
@Getter
// 기본 생성자 자동 추가
// ex) public Posts(0 {}와 같은 효과
@NoArgsConstructor
// 테이블과 링크될 클래스임을 나타낸다.
// 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭한다.
// ex) SalesManager.java -> sales_manager_table
@Entity
public class Posts extends BaseTimeEntity {
    // 해당 테이블의 PK 필드를 나타낸다.
    @Id
    // PK 생성 규칙을 나타낸다.
    // 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment 가 된다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 웬만하면 Entity의 PK는 Long 타입의 Auto_increment를 추천한다.
    // 주민등록번화와 같이 비즈니스상 유니크 키나, 여러 키를 조합한 복합키로 PK를 잡을 경우 난감한 상황이 종종 발생한다.
    private Long id;

    // 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다.
    // 사용하는 이유 : 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다.
    // 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나, 타입을 TEXT로 변경하고 싶거나 등의 경우에 사용된다.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 해당 클래스의 빌더 패턴 클래스를 생성
    // 생성자 상단에 선언 시 생성자에 포함된 플드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
