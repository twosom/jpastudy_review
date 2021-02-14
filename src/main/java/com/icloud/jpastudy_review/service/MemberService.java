package com.icloud.jpastudy_review.service;


import com.icloud.jpastudy_review.domain.Member;
import com.icloud.jpastudy_review.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service/*
            이 어노테이션이 붙어있늕 클래스는 <context:component-scan>에 의해 스프링 빈으로 등록된다.
        */

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    /*
        회원가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /*
        전체 회원 조회
     */

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*
        회원 한명만 조회
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


    //=================================================//

    /*
        중복 검증 메소드
     */
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
