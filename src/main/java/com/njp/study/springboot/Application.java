package com.njp.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// JPA Auditing 활성화
@EnableJpaAuditing
// 스프링 부트의 자동설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
// 항상 프로젝트의 최상단에 위치해야 한다.
@SpringBootApplication
public class Application {  // 앞으로 만들 프로젝트의 메인 클래스
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        // 내장 WAS(Web Application Server)를 실행.
        // 내장 WAS: 별도로 외부에 WAS를 두지 않고, 애플리케이션을 실행할 때 내부에서 WAS를 실행하는 것을 의미.
        // 항상 서버에 톰캣을 설치할 필요가 없게 되고, 스프링 부트로 만들어진 Jar 파일로 실행하면 된다.
    }
}
