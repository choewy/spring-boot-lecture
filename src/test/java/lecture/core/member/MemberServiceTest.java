package lecture.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lecture.core.AppConfig;

public class MemberServiceTest {
    AnnotationConfigApplicationContext applicationContext;
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = applicationContext.getBean(MemberService.class);
    }

    @AfterEach
    public void afterEach() {
        applicationContext.close();
    }

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.joinMember(member);
        Member findMember = memberService.findMember(member.getId());

        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
