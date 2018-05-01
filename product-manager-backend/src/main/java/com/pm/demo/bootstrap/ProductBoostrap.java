package com.pm.demo.bootstrap;

import com.pm.demo.dto.ProductDTO;
import com.pm.demo.entities.Product;
import com.pm.demo.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elimane on Apr, 2018, at 01:22
 */
@Slf4j
@Component
public class ProductBoostrap implements ApplicationListener<ContextRefreshedEvent>{


    private final ProductRepository productRepository;

    public ProductBoostrap(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        log.debug("Loading bootstrap data.....");
//        productRepository.saveAll(getProducts());

    }

    private List<Product> getProducts()
    {

        List<Product> products = new ArrayList<>();

return null;



    }
}
