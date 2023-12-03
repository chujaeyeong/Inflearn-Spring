package hellojpa_ValueType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain_JPQL {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            List<Member_ValueType> result = em.createQuery("select m from Member_ValueType m where m.username like '%kim%'",
                    Member_ValueType.class).getResultList();

            for (Member_ValueType member : result) {
                System.out.println("member = " + member);
            }


            tx.commit();

        } catch (Exception e) {
            System.out.println("Commit failed with exception: " + e.getMessage()); // 예외 발생 시 출력
            tx.rollback();
        } finally {
            emf.close();
        }



        emf.close();
    }
}
