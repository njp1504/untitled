package com.njp.study.springboot.web.dto;

import com.njp.study.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** DTO **/

// 계층 간에 데이터 교환을 위한 객체
// 뷰 템플릿 엔진에서 사용될 객체나,
// Repository Layer에서 결과로 넘겨준 객체 등
// Controller와 Service에서 사용
// Entity 클래스와 거의 유사한 형태임에도 Dto 클래스를 추가로 생성했다.


    // 하지만 절대 Entity 클래스를 Request/Response 클래스로 사용해서는 안 된다.
    // Entity 클래스가 변경되면 여러 클래스에 영향을 끼치지만,
    // Request/Response 용 Dto는 View를 위한 클래스라 정말 자주 변경이 필요하다.
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}
