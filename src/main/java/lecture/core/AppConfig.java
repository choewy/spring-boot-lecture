package lecture.core;

import lecture.core.discount.RateDiscountPolicy;
import lecture.core.member.MemberService;
import lecture.core.member.MemberServiceImpl;
import lecture.core.member.MemoryMemberRepository;
import lecture.core.order.OrderService;
import lecture.core.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
    }
}
