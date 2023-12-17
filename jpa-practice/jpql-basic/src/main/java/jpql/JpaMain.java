package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setAge(0);
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setAge(0);
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setAge(0);
            member3.setTeam(teamB);
            em.persist(member3);

//            em.flush();
//            em.clear();



            // 벌크 연산 : 쿼리 한 번으로 여러 테이블 로우 변경 (엔티티) -> 영속성 컨텍스트를 무시하고 데이터베이스에 데이터를 직접 쿼리함
            // 예제 : 모든 회원의 나이를 20살로 변경
            // 벌크 연산은 flush 안 해도 자동 호출됨, 근데 영속성 컨텍스트에는 반영이 안 되어있는 상태
            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();

            em.clear(); // 벌크 연산 수행 후 영속성 컨텍스트를 초기화해줘야 반영된다 (이거 안 하면 DB에만 반영되고 영속성 컨텍스트에는 반영이 안 된 상태가 됨)

            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("findMember = " + findMember.getAge());



//            // Named 쿼리 (스프링 데이터 JPA랑 활용하면 좋음)
//            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
//                    .setParameter("username", "회원1")
//                    .getResultList();
//
//            for (Member member : resultList) {
//                System.out.println("member = " + member);
//            }


//            // 엔티티 직접 사용 (엔티티를 파라미터로 전달)
//            String query = "select m from Member m where m = :member";
//            Member findMember = em.createQuery(query, Member.class)
//                    .setParameter("member", member1)
//                    .getSingleResult();
//
//            System.out.println("findMember = " + findMember);

//            // 엔티티 직접 사용 (식별자를 직접 전달)
//            String query = "select m from Member m where m.id = :memberId";
//            Member findMember = em.createQuery(query, Member.class)
//                    .setParameter("memberId", member1.getId())
//                    .getSingleResult();
//
//            System.out.println("findMember = " + findMember);

//            // 엔티티 직접 사용 (외래키 값)
//            String query = "select m from Member m where m.team = :team"; // TEAM_ID
//            List<Member> members = em.createQuery(query, Member.class)
//                    .setParameter("team", teamA)
//                    .getResultList();
//
//            for (Member member : members) {
//                System.out.println("member = " + member);
//            }



//            // 페치 조인의 한계 - 페이징 API 사용
//            // 원래는 페이징 API 사용이 안 되는데 (setFirstResult, setMaxResults) 일대일, 다대일 같은 단일값 연관 필드들은 페치 조인해도 페이징 가능
//            String query = "select t from Team t";
//            List<Team> result = em.createQuery(query, Team.class)
//                    .setFirstResult(0)
//                    .setMaxResults(2)
//                    .getResultList();
//
//            System.out.println("result = " + result.size());
//
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + "| members = " + team.getMembers());
//                for (Member member : team.getMembers()) {
//                    System.out.println("-> member = " + member);
//                }
//            }

//            // 페치 조인 - 일대다로 데이터 뻥튀기 + DISTINCT 로 중복 결과 제거
//            String query = "select distinct t from Team t join fetch t.members";
//            List<Team> result = em.createQuery(query, Team.class).getResultList();
//
//            System.out.println("result = " + result.size());
//
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + "| members = " + team.getMembers());
//                for (Member member : team.getMembers()) {
//                    System.out.println("-> member = " + member);
//                }
//            }

//            // 페치 조인 (fetch join)
//            String query = "select m from Member m join fetch m.team";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//
//            for (Member member : result) {
//                System.out.println("member = " + member.getUsername() + ", team name = " + member.getTeam().getName());
//                // 페치 조인 안 쓰는 경우
//                // 회원1, 팀A(SQL)
//                // 회원2, 팀A(1차캐시)
//                // 회원3, 팀B(SQL)
//                // ... 회원 100명 -> N+1 문제 발생 (쿼리가 각각 나감)
//
//                // 페지 조인을 사용하면 회원과 팀을 함께 조회해서 지연 로딩 발생 X
//                // 지연 로딩으로 설정했는뎁쇼? -> 페치 조인이 우선이라 페치 조인을 사용하면 이거는 즉시 로딩됨
//            }


            // 경로표현식
//            String query = "select m.username from Member m"; // 상태필드 : 경로 탐색의 끝, 탐색 X
//            String query = "select m.team from Member m"; // 단일 값 연관 경로 : 묵시적 내부 조인 (inner join) 발생, 탐색 O -> 쿼리 튜닝이 힘들어서 조심해서 쓰자
//            String query = "select t.members from Team t"; // 컬렉션 값 연관 경로 : 묵시적 내부 조인 발생, 탐색 X
//            String query = "select t.members.size from Team t"; // 컬렉션 값 연관 경로 탐색은 size 만 된다
//            String query = "select m.username from Team t join t.members m"; // form 절에서 명시적 조인을 통해 별칭을 얻으면 별칭을 통해 탐색 가능
//            // -> 그냥 묵시적 조인 들어가는건 실무 사용 권장 X, 조인은 무조건 명시적 조인으로 사용!!!
//
//            List<Collection> result = em.createQuery(query, Collection.class).getResultList();
//
//            System.out.println("result = " + result);


//            JPQL 함수
//            String query = "select 'a' || 'b' from Member m"; // concat
//            String query = "select substring(m.username, 2, 3) from Member m"; // substring
//            String query = "select locate('de', 'abcdef') from Member m"; // locate
//
//            List<Integer> result = em.createQuery(query, Integer.class).getResultList();
//            for (Integer s : result) {
//                System.out.println("s = " + s);
//            }


//            // 조건 CASE 식 (사용자 이름이 없으면 이름 없는 회원을 반환)
//            String query = "select coalesce(m.username, '이름 없는 회원') from Member m";
//            List<String> result = em.createQuery(query, String.class).getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

//            // 조건 CASE 식 (사용자 이름이 '관리자'면 null을 반환하고 나머지는 본인의 이름을 반환)
//            String query = "select nullif(m.username, '관리자') as username from Member m";
//            List<String> result = em.createQuery(query, String.class).getResultList();
//
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }


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
