package com.icloud.jpastudy_review.repository;

import com.icloud.jpastudy_review.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository /*
                @Repository 애노테이션이 붙으면 <context:component-scan>에 의해 스프링빈으로 자동 등록.
                JPA 전용 예외가 발생하면 스프링이 추상화한 예외로 변환해줌.
            */
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    //==회원 저장==//
    public void save(Member member) {
        em.persist(member);
    }

    //회원 한명 조회==//
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    //회원 여러명 조회==//
    public List<Member> findAll() {
        return em.createQuery(
                "select m " +
                        "from Member m ", Member.class)
                .getResultList();
    }

    //회원 이름을 조회
    public List<Member> findByName(String name) {
        return em.createQuery(
                "select m " +
                        "from Member m " +
                        "where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
