package com.service.backoffice.mapper;

import com.service.backoffice.dto.OrderDto;
import com.service.backoffice.dto.OrderDtoFromTripService;
import com.service.backoffice.model.Order;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderMapperUtil.class})
public abstract class MapperForOrder {
    @Mapping(source = "tariffId", target = "tariff", qualifiedByName = "tariffById")
    public abstract Order toOrder(OrderDto orderDto);

    @Mapping(source = "tariffId", target = "carType", qualifiedByName = "carTypeByTariffId")
    public abstract OrderDto toOrderDtoFromTripService(OrderDtoFromTripService orderDto);

    public abstract List<Order> toOrders(List<OrderDto> orderDtos);

    @Mapping(source = "tariff", target = "tariffId", qualifiedByName = "idFromTariff")
    public abstract OrderDto toOrderDto(Order order);

    public abstract List<OrderDto> toOrderDtos(List<Order> orders);
}
