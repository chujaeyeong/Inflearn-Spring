package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();

            System.out.println("result = " + result.getUsername());


//            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class); // 반환 타입이 명확할 때 사용
//            Query query3 = em.createQuery("select m.username, m.age from Member m"); // 반환 타입이 명확하지 않을 때 사용

//            // 결과가 하나 이상일 때 리스트 반환 -> 결과가 없으면 빈 리스트 반환 (널포인트익셉션 X)
//            List<Member> resultList = query.getResultList();
//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }
//
//            // 결과가 정확히 하나일 때 단일 객체를 반환 (결과가 없으면 NoResultException, 둘 이상이면 NoUniqueResultException)
//            Member result = query.getSingleResult();
//            System.out.println("result = " + result);



            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
