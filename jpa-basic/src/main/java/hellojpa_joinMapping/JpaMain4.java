package hellojpa_joinMapping;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain4 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member3 member3 = new Member3();
            member3.setUsername("member1");
            member3.setTeam(team);

            em.persist(member3);

            em.flush();
            em.clear();

            Member3 m = em.find(Member3.class, member3.getId());

            System.out.println("m = " + m.getTeam().getClass());

            System.out.println("=================");
            m.getTeam().getName(); // 초기화
            System.out.println("=================");

            tx.commit();

        } catch (Exception e) {

            tx.rollback();

        } finally {

            em.close();
        }

        emf.close();
    }
}
