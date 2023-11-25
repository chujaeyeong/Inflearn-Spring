package hellojpa_joinMapping;

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
            member3.setUsername("user1");
            member3.setCreatedBy("kim");
            member3.setCreatedDate(LocalDateTime.now());

            em.persist(member3);

            em.flush();
            em.clear();

            tx.commit();

        } catch (Exception e) {

            tx.rollback();

        } finally {

            em.close();
        }

        emf.close();
    }
}
