package study.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.springdatajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
