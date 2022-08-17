package com.service.backoffice.mapper;

import com.service.backoffice.dto.OrderDto;
import com.service.backoffice.model.Order;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-16T16:11:16+0300",
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

        int userId = 0;
        String carType = null;
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        int prise = 0;
        int carId = 0;

        userId = orderDto.getUserId();
        carType = orderDto.getCarType();
        startDate = orderDto.getStartDate();
        endDate = orderDto.getEndDate();
        prise = orderDto.getPrise();
        carId = orderDto.getCarId();

        Long id = null;

        Order order = new Order( id, startDate, endDate, prise, carId, carType, userId );

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
