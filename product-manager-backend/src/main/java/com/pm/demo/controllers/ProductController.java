package com.pm.demo.controllers;

import com.pm.demo.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Elimane on Apr, 2018, at 14:10
 */
@Controller
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }



}
