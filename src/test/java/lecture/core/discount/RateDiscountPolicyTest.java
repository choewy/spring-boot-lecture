package lecture.core.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lecture.core.member.Grade;
import lecture.core.member.Member;

public class RateDiscountPolicyTest {
    RateDiscountPolicy discountpolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%g 할인이 적용되어야 한다")
    void vip_o() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        int discount = discountpolicy.discount(member, 10000);

        // then
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void vip_x() {
        // given
        Member member = new Member(1L, "memberA", Grade.BASIC);

        // when
        int discount = discountpolicy.discount(member, 10000);

        // then
        Assertions.assertThat(discount).isEqualTo(0);
    }
}
