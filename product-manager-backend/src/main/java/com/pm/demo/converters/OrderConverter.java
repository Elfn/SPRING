package com.pm.demo.converters;

import com.pm.demo.dto.OrderDTO;
import com.pm.demo.dto.ProductDTO;
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
 * Created by Elimane on Apr, 2018, at 04:26
 */
@Component
public class OrderConverter implements Converter<Order, OrderDTO> {


    @Synchronized
    @Nullable
    @Override
    public OrderDTO convert(Order source) {

        if (source == null) {
            return null;
        }

        OrderDTO dto = new OrderDTO();

        dto.setId(source.getId());
        dto.setName(source.getName());
        dto.setDateoforder(source.getDateoforder());

        if(source.getProduct() != null)
        {
            dto.setProductID(source.getProduct().getId());
        }

        return dto;
    }

    public OrderDTO orderToOrderDTO(Order order) {
        return this.convert(order);
    }


    public Set<OrderDTO> productsToProductDTOs(Set<Order> orders) {
        return orders.stream()
                .filter(Objects::nonNull)
                .map(this::orderToOrderDTO)
                .collect(Collectors.toSet());
    }



}
