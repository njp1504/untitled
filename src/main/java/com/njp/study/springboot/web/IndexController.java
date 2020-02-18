package com.njp.study.springboot.web;

import com.njp.study.springboot.service.posts.PostsService;
import com.njp.study.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// index.mustache url 연동
// 페이지에 관련된 컨트롤러는 모두 IndexController를 사용한다.

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;


    // 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때
    // 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.
    // 앞의 경로: src/main/resources/templates
    // 뒤의 파일 확장자 : .mustache
//    @GetMapping("/")
//    public String index() {
//        // src/main/resources/templates/index.mustache로 전화되어 View Resolver가 처리한다.
//        return "index";
//    }
    @GetMapping("/")
    public String index(Model model) {
        // Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    // /posts/save를 호출하면 posts-save.maustache를 호출하는 메소드
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

}
