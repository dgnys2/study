package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // Spring 이랑 엮어서 실행하기 위함.
@SpringBootTest // Spring context 안에서 테스트를 실행하기 위함.
@Transactional // 롤백 위해
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    public void 회원가입() throws Exception {

        //given
        Member member = new Member();
        member.setName("kim1");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member,memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {

        //given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        //when

        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다.


        //then
        fail("예외가 발생해야 한다.");

    }


}