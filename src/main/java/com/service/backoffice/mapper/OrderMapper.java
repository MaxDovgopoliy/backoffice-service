package com.service.backoffice.mapper;

import com.service.backoffice.dto.OrderDto;
import com.service.backoffice.model.Order;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    OrderDto toOrderDto(Order order);

    Order toOrder(OrderDto orderDto);

    List<OrderDto> toOrderDtos(List<Order> orders);

    List<Order> toOrders(List<OrderDto> orderDtos);

}
