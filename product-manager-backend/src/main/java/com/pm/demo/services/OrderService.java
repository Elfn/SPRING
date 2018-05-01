package com.pm.demo.services;

import com.pm.demo.dto.OrderDTO;

import java.util.Set;

/**
 * Created by Elimane on Apr, 2018, at 01:37
 */
public interface OrderService {

    public Set<OrderDTO> getOrders();
    // public ProductDTO findById(Long id);
    public OrderDTO saveProduct(OrderDTO orderDTO);
    public OrderDTO findOrderById(Long id);
    public void deleteOrder(String id);

}
