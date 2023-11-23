package hellojpa_joinMapping;

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

            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member3 member = new Member3();
            member.setUsername("member1");
            member.changeTeam(team); // **
            em.persist(member);

            team.addMember(member); // 연관관계 편의 메서드 설정은 1에 해도 ㄱㅊ 다에 해도 ㄱㅊ 근데 한군데에만 하자

//            team.getMembers().add(member);
            // 이거 안 넣으면 값을 제대로 불러오지 못 함 1차 캐시에 없으니까 -> 순수 객체 상태를 고려해서 항상 양쪽에 값을 설정하자

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
            List<Member3> members = findTeam.getMembers();

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
