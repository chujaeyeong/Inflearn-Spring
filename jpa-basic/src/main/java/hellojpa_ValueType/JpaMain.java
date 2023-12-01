package hellojpa_ValueType;

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

            Address address = new Address("city", "street", "10000");

            Member_ValueType member = new Member_ValueType();
            member.setUsername("member1");
            member.setHomeAdress(address);
            em.persist(member);

            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

            Member_ValueType member2 = new Member_ValueType();
            member2.setUsername("member2");
            member2.setHomeAdress(member.getHomeAdress());
            em.persist(member2);

            tx.commit();

            System.out.println("Commit successful");

        } catch (Exception e) {
            System.out.println("Commit failed with exception: " + e.getMessage()); // 예외 발생 시 출력
            tx.rollback();

        } finally {
            em.close();
        }

        emf.close();
    }
}
