package com.service.backoffice.mapper;

import com.service.backoffice.dto.OrderDTO;
import com.service.backoffice.dto.TariffDTO;
import com.service.backoffice.model.Order;
import com.service.backoffice.model.Tariff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    OrderDTO toOrderDTO(Order order);

    Order toOrder(OrderDTO orderDTO);

    List<OrderDTO> toOrderDTOs(List<Order> orders);

    List<Order> toOrders( List<OrderDTO> orderDTOs);
}
