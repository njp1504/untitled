package com.njp.study.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


// 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
// SpringRunner라는 스프링 실행자를 사용한다.
// 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
@RunWith(SpringRunner.class)

// 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션 이다.
// 선언할 경우, @Controller, @ControllerAdvice 등을 사용할 수 있다.
// 단, @Service, @Component, @Repository등은 사용할 수 없다.
// 여기서는 컨트롤러만 사용하기 때문에 선언한다.
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    // 스프링이 관리하는 빈(Bean)을 주입 받는다.
    @Autowired
    // 웹 API를 테스트할 때 사용한다.
    // 스프링 MVC 테스트의 시작점이다.
    // 이 클래스를 통해 HTTP, GET, POST 등에 대한 API 테스트를 할 수 있다.
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))      // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 한다. 체이닝 지원
                .andExpect(status().isOk())         // mvc.perform의 결과를 검증. HTTP Header의 Status를 검증. (200, 404, 500 등 상태)
                .andExpect(content().string(hello));    // mvc.perform의 결과를 검증. 응답 본문의 내용을 검증. Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증.
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                .param("name", name)                                // API 테스트할 때 사용될 요청 파라미터를 설정한다. (값은 String만 허용된다.)
                .param("amount", String.valueOf(amount)))           // String만 허용되기 때문에 변환
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))        // JSON 응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드명을 명시한다.
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
