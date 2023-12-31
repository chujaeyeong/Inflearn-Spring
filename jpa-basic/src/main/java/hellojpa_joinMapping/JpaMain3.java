package hellojpa_joinMapping;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain3 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member3 member3 = new Member3();
            member3.setUsername("member1");
            em.persist(member3);

            em.flush();
            em.clear();

            Member3 refMember = em.getReference(Member3.class, member3.getId());
            System.out.println("refMember = " + refMember.getClass()); // 프록시 : 데이터베이스 조회를 미루는 가짜 (프록시) 엔티티 객체 조회
            refMember.getUsername(); // 강제호출
            Hibernate.initialize(refMember); // 강제초기화
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember)); // 프록시 인스턴스의 초기화 여부 확인

            tx.commit();

        } catch (Exception e) {

            tx.rollback();

        } finally {

            em.close();
        }

        emf.close();
    }
}
