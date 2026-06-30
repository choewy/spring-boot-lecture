package lecture.core.discount;

import lecture.core.member.Grade;
import lecture.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {
    private final int discountFixedAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixedAmount;
        }

        return 0;
    }
}
