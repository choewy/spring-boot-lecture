package lecture.core.bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lecture.core.AppConfig;
import lecture.core.member.MemberService;
import lecture.core.member.MemberServiceImpl;

public class ApplicationContextTest {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 조회")
    void findAllBeans() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);
            System.out.println("name=" + beanDefinitionName + ", object=" + bean);
        }
    }

    @Test
    @DisplayName("이름으로 빈 조회")
    void findBeanByName() {
        MemberService bean = applicationContext.getBean("memberService", MemberService.class);
        assertThat(bean).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("타입으로 빈 조회")
    void findBeanByType() {
        MemberService bean = applicationContext.getBean(MemberService.class);
        assertThat(bean).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("구현체 타입으로 빈 조회")
    void findBeanByImpl() {
        MemberService bean = applicationContext.getBean("memberService", MemberServiceImpl.class);
        assertThat(bean).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 조회 실패")
    void findBeanFailed() {
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> applicationContext.getBean("x", MemberService.class));
    }
}
