package ru.bunkov.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bunkov.market.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//    List<Order> findAllByUser(User user);

    @Query("select o from Order o where o.user.username =:username")
    List<Order> findAllByUsername(String username);
}
