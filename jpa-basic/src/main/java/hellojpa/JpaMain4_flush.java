package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain4_flush {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 영속

            Member member = new Member(200L, "member200");
            em.persist(member);

            // 쿼리 날라가는걸 이 단계에서 지금 보고싶으면 플러시를 직접호출(강제호출) 해주면 된다 (원래는 커밋할때 날라감)
            // 플러시를 해도 1차캐시는 안 지워짐 (안건드림)
            // JPQL 을 쓰면 자동으로 플러시가 호출됨
            em.flush();

            System.out.println("=============================");

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
