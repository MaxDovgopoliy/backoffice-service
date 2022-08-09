package com.service.backoffice.mapper;

import com.service.backoffice.dto.OrderDTO;
import com.service.backoffice.model.Order;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-09T12:54:40+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.16 (Amazon.com Inc.)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO toOrderDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setStartDate( order.getStartDate() );
        orderDTO.setEndDate( order.getEndDate() );
        orderDTO.setPrise( order.getPrise() );
        orderDTO.setCarId( order.getCarId() );
        orderDTO.setCarType( order.getCarType() );
        orderDTO.setUserId( order.getUserId() );

        return orderDTO;
    }

    @Override
    public Order toOrder(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setUserId( orderDTO.getUserId() );
        order.setCarType( orderDTO.getCarType() );
        order.setStartDate( orderDTO.getStartDate() );
        order.setEndDate( orderDTO.getEndDate() );
        order.setPrise( orderDTO.getPrise() );
        order.setCarId( orderDTO.getCarId() );

        return order;
    }

    @Override
    public List<OrderDTO> toOrderDTOs(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>( orders.size() );
        for ( Order order : orders ) {
            list.add( toOrderDTO( order ) );
        }

        return list;
    }
}
