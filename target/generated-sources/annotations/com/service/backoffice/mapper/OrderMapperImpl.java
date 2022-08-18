package com.service.backoffice.mapper;

import com.service.backoffice.dto.OrderDto;
import com.service.backoffice.model.Order;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-18T13:00:34+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.16 (Amazon.com Inc.)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDto toOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setStartDate( order.getStartDate() );
        orderDto.setEndDate( order.getEndDate() );
        orderDto.setPrise( order.getPrise() );
        orderDto.setCarId( order.getCarId() );
        orderDto.setCarType( order.getCarType() );
        orderDto.setUserId( order.getUserId() );

        return orderDto;
    }

    @Override
    public Order toOrder(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setUserId( orderDto.getUserId() );
        order.setCarType( orderDto.getCarType() );
        order.setStartDate( orderDto.getStartDate() );
        order.setEndDate( orderDto.getEndDate() );
        order.setPrise( orderDto.getPrise() );
        order.setCarId( orderDto.getCarId() );

        return order;
    }

    @Override
    public List<OrderDto> toOrderDtos(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderDto> list = new ArrayList<OrderDto>( orders.size() );
        for ( Order order : orders ) {
            list.add( toOrderDto( order ) );
        }

        return list;
    }

    @Override
    public List<Order> toOrders(List<OrderDto> orderDtos) {
        if ( orderDtos == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( orderDtos.size() );
        for ( OrderDto orderDto : orderDtos ) {
            list.add( toOrder( orderDto ) );
        }

        return list;
    }
}
