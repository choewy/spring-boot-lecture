package lecture.core.discount;

import lecture.core.member.Grade;
import lecture.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() != Grade.VIP) {
            return 0;
        }

        return price * discountPercent / 100;
    }

}
