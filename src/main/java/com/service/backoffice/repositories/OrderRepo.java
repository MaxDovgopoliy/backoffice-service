package com.service.backoffice.repositories;

import com.service.backoffice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
     List<Order> findAllByUserIdAndStartDateBetween(Long userId, LocalDate startDate,LocalDate endDate);
}
