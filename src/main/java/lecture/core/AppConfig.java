package lecture.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lecture.core.discount.DiscountPolicy;
import lecture.core.discount.RateDiscountPolicy;
import lecture.core.member.MemberRepository;
import lecture.core.member.MemberService;
import lecture.core.member.MemberServiceImpl;
import lecture.core.member.MemoryMemberRepository;
import lecture.core.order.OrderService;
import lecture.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {
    @Bean
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
