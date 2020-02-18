package com.njp.study.springboot.web;

import com.njp.study.springboot.service.posts.PostsService;
import com.njp.study.springboot.web.dto.PostsResponseDto;
import com.njp.study.springboot.web.dto.PostsSaveRequestDto;
import com.njp.study.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/** Web Model **/
// 외부 요청과 응답에 대한 전반적인 영역

// 스프링에서 Bean 객체를 받는 형식으로, 생성자로 Bean 객체를 주입받는 형식을 권장한다.
// (= @Autowired)
// 그럼 생성자는? @RequiredArgsConstructor에서 해결해 준다.
// final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복의 @RequiredArgsConstructor가 대신 생성해 준다.
// 생성자를 직접 안쓰고 롬복 어노테이션을 사용한 이유는:
// 해당 클래스의 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결하기 위함.

@RequiredArgsConstructor
@RestController                 // JSON을 반환하는 컨트롤러로 만들어 준다.
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto
                                            requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
