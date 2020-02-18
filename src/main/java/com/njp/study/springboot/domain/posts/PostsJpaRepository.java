package com.njp.study.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/** Repository Model **/

// Jpa에서는 DB Layer 접근자를 Repository 라고 부른다.
// 단순히 인터페이스를 생성 후, JpaRepository<Entity 클래스, PK 타입> 를 상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.
// Entity 클래스와 기본 Entity Repository는 디렉토리에 함께 위치해야 한다.
public interface PostsJpaRepository extends JpaRepository<Posts, Long> {
    // 직접 쿼리를 입력할 수 있다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
