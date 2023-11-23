package hellojpa_joinMapping;

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

            //팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            //회원 저장
            Member3 member = new Member3();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            em.flush(); // 영속성 컨텍스트에 있는 1차 캐시를 DB로 전달
            em.clear(); // 영속성 컨텍스트 비워주기

            Member3 findMember = em.find(Member3.class, member.getId());
            List<Member3> members = findMember.getTeam().getMembers();

            for (Member3 m : members) {
                System.out.println("m = " + m.getUsername());
            }

            tx.commit();

        } catch (Exception e) {

            tx.rollback();

        } finally {

            em.close();
        }

        emf.close();
    }
}
