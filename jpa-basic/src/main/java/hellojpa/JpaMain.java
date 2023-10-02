package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // select
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

//            insert
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

//            delete
//            em.remove(findMember);

//            update (em.persist 안 해줘도됨 JPA가 관리해주기때문에)
//            findMember.setName("HelloJPA");


//            JPQL select (2개 이상의 데이터를 조회하고 싶을 때 사용)
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .getResultList();
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

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
