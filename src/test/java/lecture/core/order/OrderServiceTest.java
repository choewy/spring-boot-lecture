package lecture.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lecture.core.AppConfig;
import lecture.core.member.Grade;
import lecture.core.member.Member;
import lecture.core.member.MemberService;

public class OrderServiceTest {
    AnnotationConfigApplicationContext applicationContext;
    private MemberService memberService;
    private OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        this.memberService = applicationContext.getBean(MemberService.class);
        this.orderService = applicationContext.getBean(OrderService.class);
    }

    @AfterEach
    public void afterEach() {
        applicationContext.close();
    }

    @Test
    void createOrder() {
        // given
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.joinMember(member);

        String itemName = "itemA";
        int orderPrice = 10000;

        // when
        Order order = orderService.createOrder(memberId, itemName, orderPrice);

        // then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        Assertions.assertThat(order.calculatePrice()).isEqualTo(order.getItemPrice() - order.getDiscountPrice());
    }
}
