package lecture.core;

import lecture.core.member.Grade;
import lecture.core.member.Member;
import lecture.core.member.MemberService;
import lecture.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.joinMember(member);

        Member findMember = memberService.findMember(member.getId());

        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
