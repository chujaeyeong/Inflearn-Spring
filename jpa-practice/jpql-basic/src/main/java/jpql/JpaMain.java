package jpql;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(10);
            member.setType(MemberType.ADMIN);

            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();


//            // 조건 CASE 식 (사용자 이름이 없으면 이름 없는 회원을 반환)
//            String query = "select coalesce(m.username, '이름 없는 회원') from Member m";
//            List<String> result = em.createQuery(query, String.class).getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

            // 조건 CASE 식 (사용자 이름이 '관리자'면 null을 반환하고 나머지는 본인의 이름을 반환)
            String query = "select nullif(m.username, '관리자') as username from Member m";
            List<String> result = em.createQuery(query, String.class).getResultList();

            for (String s : result) {
                System.out.println("s = " + s);
            }


//            // 기본 CASE 식
//            String query =
//                    "select " +
//                            "case when m.age <= 10 then '학생요금' " +
//                            "when m.age >= 60 then '경로요금' " +
//                            "else '일반요금' " +
//                            "end " +
//                    "from Member m";
//
//            List<String> result = em.createQuery(query, String.class).getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }


//            // JPQL 타입 표현
//            String query = "select m.username, 'HELLO', true from Member m where m.type = jpql.MemberType.ADMIN";
//            List<Object[]> result = em.createQuery(query).getResultList();
//
//            for (Object[] objects : result) {
//                System.out.println("objects = " + objects[0]);
//                System.out.println("objects = " + objects[1]);
//                System.out.println("objects = " + objects[2]);
//            }


//            // 서브 쿼리
//            String query = "select (select avg(m1.age) from Member m1) as avgAge from Member m join Team t on m.username = t.name";
//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();
//
//            System.out.println("result = " + result.size());


            // 조인
//            String query = "select m from Member m inner join m.team t";
//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();

//            // 세타 조인
//            String query = "select m from Member m, Team t where m.username = t.name";
//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();

            // 조인 ON 절
//            String query = "select m from Member m left join m.team t on t.name = 'teamA'";
//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();
//
//            System.out.println("result = " + result.size());


//            // 페이징
//            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            System.out.println("result.size = " + result.size());
//            for (Member member1 : result) {
//                System.out.println("member1 = " + member1);
//            }


            // 프로젝션 (SELECT)
//            List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList(); // 엔티티 프로젝션
//            List<Team> result = em.createQuery("select m.team from Member m", Team.class).getResultList(); // 엔티티 프로젝션 (이렇게 하면 돌아가긴 하는데 헷갈리니까 조인 문법으로 써야된다)
//            em.createQuery("select o.address from Order o", Address.class).getResultList(); // 임베디드 타입 프로젝션 (소속을 꼭 맞춰서 적어야된다)
//            em.createQuery("select distinct m.username, m.age from Member m").getResultList(); // 스칼라 타입 프로젝션, DISTINCT로 중복 제거

            // 프로젝션 - 여러값 조회 : new 명령어로 조회
//            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
//                    .getResultList();
//
//            MemberDTO memberDTO = result.get(0);
//            System.out.println("memberDTO = " + memberDTO.getUsername());
//            System.out.println("memberDTO = " + memberDTO.getAge());


//            Member findMember = result.get(0);
//            findMember.setAge(20);


//            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
//                    .setParameter("username", "member1")
//                    .getSingleResult();
//
//            System.out.println("result = " + result.getUsername());

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
