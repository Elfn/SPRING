package com.pm.demo.converters;

import com.pm.demo.dto.OrderDTO;
import com.pm.demo.entities.Order;
import com.pm.demo.entities.Product;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Elimane on Apr, 2018, at 01:32
 */
@Component
public class OrderDTOConverter implements Converter<OrderDTO, Order>{




    @Synchronized
    @Nullable
    @Override
    public Order convert(OrderDTO source) {


        if (source == null) {
            return null;
        }

        Order order = new Order();

        order.setId(source.getId());
        order.setName(source.getName());
        order.setDateoforder(source.getDateoforder());

        if(source.getProductID() != null)
        {
            Product product = new Product();

            product.setId(source.getProductID());
            order.setProduct(product);
            product.addOrder(order);

        }

        return order;
    }







    public Order orderToOrderDTO(OrderDTO orderDTO) {
        return this.convert(orderDTO);
    }


    public Set<Order> orderDTOsToOrders(Set<OrderDTO> orderDTOs) {
        return orderDTOs.stream()
                .filter(Objects::nonNull)
                .map(this::orderToOrderDTO)
                .collect(Collectors.toSet());
    }

}
