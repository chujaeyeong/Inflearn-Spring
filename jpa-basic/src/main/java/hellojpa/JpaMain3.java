package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain3 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 영속

            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA2"); // 변경내용 추적, 더티체킹

            // 영속 상태의 엔티티가 영속성 컨텍스트에서 분리
            // 영속성 컨텍스트가 제공하는 기능을 사용 못 함 >> 준영속상태, detach 상태라고 함
            // detach(entity) : 특정 엔티티만 준영속 상태로 전환
            // em.clear() : 영속성 컨텍스트를 완전히 초기화
            // em.close() : 영속성 컨텍스트를 종료
//            em.detach(member);
            em.clear();

            Member member2 = em.find(Member.class, 150L);

            System.out.println("=====================");

            tx.commit();

        } catch (Exception e) {

            tx.rollback();

        } finally {

            em.close();
        }

        // DB를 다 쓰면 닫아줘야된다
        emf.close();
    }
}
