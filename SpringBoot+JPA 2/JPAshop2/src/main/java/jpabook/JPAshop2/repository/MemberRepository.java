package jpabook.JPAshop2.repository;

import jpabook.JPAshop2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // select m from Member m where m.name = ?
    List<Member> findByName(String name);
}
