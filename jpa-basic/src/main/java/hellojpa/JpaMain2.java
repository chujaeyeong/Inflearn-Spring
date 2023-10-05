package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // 비영속상태
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA2");

            // 여기서부터 영속상태로 바뀜 (근데 여기 상태에서 DB에 저장되진 않음, commit 할 때 날라감 뒤에서 상세하게 배움!)
//            System.out.println("=== BEFORE ===");
//            em.persist(member);
//            System.out.println("=== AFTER ===");

            // 영속, 처음 조회 할 때는 DB에서 가져오지만, 2번째부터는 1차 캐시에서 저장된 값을 가져온다 (DB까지 안 감)
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);

            // true, 1차 캐시로 반복 가능한 일기 (REPEAT)
            System.out.println("result = " + (findMember1 == findMember2));

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
