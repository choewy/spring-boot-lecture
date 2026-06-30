package lecture.core;

import lecture.core.discount.DiscountPolicy;
import lecture.core.discount.RateDiscountPolicy;
import lecture.core.member.MemberRepository;
import lecture.core.member.MemberService;
import lecture.core.member.MemberServiceImpl;
import lecture.core.member.MemoryMemberRepository;
import lecture.core.order.OrderService;
import lecture.core.order.OrderServiceImpl;

public class AppConfig {
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
