package com.pm.demo.repositories;

import com.pm.demo.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Elimane on Apr, 2018, at 01:34
 */

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
