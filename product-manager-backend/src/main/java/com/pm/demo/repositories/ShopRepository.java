package com.pm.demo.repositories;

import com.pm.demo.entities.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Elimane on Apr, 2018, at 01:31
 */
@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {
}
