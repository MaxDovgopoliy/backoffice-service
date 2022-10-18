package com.service.backoffice.repositories;

import com.service.backoffice.model.Order;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findAllByUserIdAndStartDateTimeBetween(Integer userId, LocalDateTime startDate,
                                                       LocalDateTime endDate);

    List<Order> findAllByCarIdAndStartDateTimeBetween(Integer userId, LocalDateTime startDate,
                                                       LocalDateTime endDate);

    List<Order> findAllByUserId(Integer userId);

    List<Order> findAllByStartDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}
