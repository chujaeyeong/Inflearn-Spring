package jpabook.JPAshop2;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JPAshop2Application {

	public static void main(String[] args) {
		SpringApplication.run(JPAshop2Application.class, args);
	}

	// 엔티티를 직접 노출할 때 사용하는 방법
	// 엔티티 직접 노출은 진짜 하지말자
	@Bean
	Hibernate5JakartaModule hibernate5Module() {
		return new Hibernate5JakartaModule();
	}

}
