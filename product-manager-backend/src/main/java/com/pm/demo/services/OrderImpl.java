package com.pm.demo.services;

import com.pm.demo.converters.OrderConverter;
import com.pm.demo.converters.OrderDTOConverter;
import com.pm.demo.dto.OrderDTO;
import com.pm.demo.entities.Order;
import com.pm.demo.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Elimane on Apr, 2018, at 01:38
 */
@Slf4j
@Service
public class OrderImpl implements OrderService {

    final private OrderConverter orderConverter;
    final private OrderDTOConverter orderDTOConverter;
    final private OrderRepository orderRepository;

    public OrderImpl(OrderConverter orderConverter, OrderDTOConverter orderDTOConverter, OrderRepository orderRepository) {
        this.orderConverter = orderConverter;
        this.orderDTOConverter = orderDTOConverter;
        this.orderRepository = orderRepository;
    }

    @Override
    public Set<OrderDTO> getOrders() {

        log.debug("I'm in the getProducts service!!!!");

        Set<OrderDTO> setOfOrderDTOs = orderConverter.productsToProductDTOs((Set<Order>) orderRepository.findAll());

        return setOfOrderDTOs;

    }

    @Override
    public OrderDTO saveProduct(OrderDTO orderDTO) {

        Order detachedOrder = orderDTOConverter.convert(orderDTO);

        Order savedOrder =orderRepository.save(detachedOrder);

        log.debug("Saved OrderId:" + savedOrder.getId());


        return orderConverter.convert(savedOrder);

    }

    @Override
    public OrderDTO findOrderById(Long id) {


        Optional<Order> foundOrder = orderRepository.findById(id);

        if(foundOrder.isPresent())
        {
            log.debug("Product object =>"+foundOrder.toString()+", not found by Id in Product Impl!!! ");
        }

        //Conversion
        OrderDTO orderToReturn = orderConverter.convert(foundOrder.get());

        return orderToReturn;

    }

    @Override
    public void deleteOrder(String id) {

        orderRepository.deleteById(Long.valueOf(id));

    }
}
