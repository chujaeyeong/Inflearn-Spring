package jpabook.jpashop;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Movie;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

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

            Book book = new Book();
            book.setName("JPA3");
            book.setName("김영한3");

            Movie movie = new Movie();
            movie.setName("AAA");

            em.persist(book);
            em.persist(movie);

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
