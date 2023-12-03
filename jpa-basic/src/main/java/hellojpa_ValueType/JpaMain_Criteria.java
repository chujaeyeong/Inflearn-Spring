package hellojpa_ValueType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain_Criteria {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // Criteria 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member_ValueType> query = cb.createQuery(Member_ValueType.class);

            // 루트 클래스 (조회를 시작할 클래스)
            Root<Member_ValueType> m = query.from(Member_ValueType.class);

            // 쿼리 생성
            CriteriaQuery<Member_ValueType> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
            List<Member_ValueType> resultList = em.createQuery(cq).getResultList();

            tx.commit();

        } catch (Exception e) {
            System.out.println("Commit failed with exception: " + e.getMessage()); // 예외 발생 시 출력
            tx.rollback();
        } finally {
            emf.close();
        }

        emf.close();
    }
}
