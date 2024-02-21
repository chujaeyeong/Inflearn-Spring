package study.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.springdatajpa.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
