package com.pm.demo.repositories;

import com.pm.demo.entities.Product;
import com.pm.demo.entities.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Elimane on Apr, 2018, at 01:34
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    public double countAllByStatus(String status);


}
