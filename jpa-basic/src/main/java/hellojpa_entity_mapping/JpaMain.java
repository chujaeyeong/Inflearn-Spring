package hellojpa_entity_mapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member2 member1 = new Member2();
            member1.setUsername("A");

            Member2 member2 = new Member2();
            member2.setUsername("B");

            Member2 member3 = new Member2();
            member3.setUsername("C");

            System.out.println("======================");

            em.persist(member1); // 1, 51
            em.persist(member2); // MEM
            em.persist(member3); // MEM

            System.out.println("member1 = " + member1);
            System.out.println("member2 = " + member2);
            System.out.println("member3 = " + member3);

            System.out.println("======================");


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
