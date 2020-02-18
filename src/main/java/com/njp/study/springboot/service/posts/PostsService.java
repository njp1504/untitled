package com.njp.study.springboot.service.posts;

import com.njp.study.springboot.domain.posts.Posts;
import com.njp.study.springboot.domain.posts.PostsJpaRepository;
import com.njp.study.springboot.web.dto.PostsListResponseDto;
import com.njp.study.springboot.web.dto.PostsResponseDto;
import com.njp.study.springboot.web.dto.PostsSaveRequestDto;
import com.njp.study.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/** Service Model **/

// 트랜잭션과 도메인 간의 순서만 보장
// 일반적으로 Controller와 Dao의 중간 영역

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsJpaRepository postsJpaRepository;

    public Long save(PostsSaveRequestDto requestDto) {
        return postsJpaRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsJpaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsJpaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    // 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선되기 때문에
    // 등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용하는 것을 추천
    @Transactional(readOnly=true)
    public List<PostsListResponseDto> findAllDesc() {
        // postsJpaRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환
        return postsJpaRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
