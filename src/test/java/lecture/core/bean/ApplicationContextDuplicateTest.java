package lecture.core.bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lecture.core.member.MemberRepository;
import lecture.core.member.MemoryMemberRepository;

public class ApplicationContextDuplicateTest {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

    @Configuration
    static class TestConfig {
        @Bean
        MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

    @Test
    @DisplayName("타입으로 조회 시 중복 오류")
    void findBeansByTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> applicationContext.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("중복 타입인 경우 빈 이름으로 지정하여 조회")
    void findBeanByName() {
        MemberRepository bean = applicationContext.getBean("memberRepository1", MemberRepository.class);
        assertThat(bean).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입으로 모든 빈 조회")
    void findBeansByType() {
        Map<String, MemberRepository> beansOfType = applicationContext.getBeansOfType(MemberRepository.class);

        for (String key : beansOfType.keySet()) {
            assertThat(beansOfType.get(key)).isInstanceOf(MemberRepository.class);
        }
    }
}
