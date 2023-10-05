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
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA2");

            // 여기서부터 영속상태로 바뀜 (근데 여기 상태에서 DB에 저장되진 않음, commit 할 때 날라감 뒤에서 상세하게 배움!)
            System.out.println("=== BEFORE ===");
            em.persist(member);
            System.out.println("=== AFTER ===");


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
