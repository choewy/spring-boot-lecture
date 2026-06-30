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

import lecture.core.discount.DiscountPolicy;
import lecture.core.discount.FixDiscountPolicy;
import lecture.core.discount.RateDiscountPolicy;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);

    @Configuration
    static class TestConfig {
        @Bean
        DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회한다.")
    void findAllBeansByParentType() {
        Map<String, DiscountPolicy> beansOfType = applicationContext.getBeansOfType(DiscountPolicy.class);

        for (String key : beansOfType.keySet()) {
            DiscountPolicy bean = beansOfType.get(key);
            assertThat(bean).isInstanceOf(DiscountPolicy.class);
        }
    }

    @Test
    @DisplayName("부모 타입으로 조회 시 자식이 둘 이상 있으면 중복 오류가 발생한다.")
    void findBeansByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> applicationContext.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회 시 자식이 둘 이상 있으면 빈 이름을 지정하면 된다.")
    void findBeansByParentTypeBeanName() {
        DiscountPolicy bean = applicationContext.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회한다.")
    void findBeansByImplType() {
        RateDiscountPolicy bean = applicationContext.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("Object 타입으로 모두 조회한다.")
    void findAllBeansByObjectType() {
        Map<String, Object> beansOfType = applicationContext.getBeansOfType(Object.class);

        for (String key : beansOfType.keySet()) {
            Object bean = beansOfType.get(key);
            System.out.println("name=" + key + ", object=" + bean);
        }
    }
}
