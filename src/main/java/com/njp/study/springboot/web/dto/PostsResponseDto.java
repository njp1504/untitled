package com.njp.study.springboot.web.dto;

import com.njp.study.springboot.domain.posts.Posts;
import lombok.Getter;

// PostsResponseDto 는 Entity의 필드 중 일부만 사용하므로
// 생성자로 Entity를 받아 필드에 값을 넣는다.
@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
