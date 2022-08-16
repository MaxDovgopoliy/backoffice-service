package com.service.backoffice.repositories;

import com.service.backoffice.model.Order;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findAllByUserIdAndStartDateBetween(Integer userId, LocalDateTime startDate,
                                                   LocalDateTime endDate);

    List<Order> findAllByUserId(Integer userId);

    List<Order> findAllByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
