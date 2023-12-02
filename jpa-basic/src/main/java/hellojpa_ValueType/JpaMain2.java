package hellojpa_ValueType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member_ValueType member = new Member_ValueType();
            member.setUsername("member1");
            member.setHomeAddress(new Address("city1", "street", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            member.getAddressHistory().add(new AddressEntity("old1", "street1", "11000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street2", "12000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("============ START ============="); // 값 타입 컬렉션은 기본값이 지연로딩전략을 사용한다 (LAZY)
            Member_ValueType findMember = em.find(Member_ValueType.class, member.getId());

//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods) {
                System.out.println("favoriteFood = " + favoriteFood);
            }

            // homeCity -> newCity
//            findMember.getHomeAddress().setCity("newCity"); -> 값 타임은 이렇게 하면 안된다
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode())); // 완전히 새로운 인스턴스로 통으로 교체해야된다

            // 치킨 -> 한식
            // 푸드도 값타입이기때문에, update 개념이 아니라 아예 지우고 다시 넣는 방식으로 해야된다
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            findMember.getAddressHistory().remove(new AddressEntity("old1", "street1", "11000")); // 정확한 값을 넣어줘야되는데, 여기서 equals와 hashCode 가 사용된다
            findMember.getAddressHistory().add(new AddressEntity("newCity1", "street1", "11000"));

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
