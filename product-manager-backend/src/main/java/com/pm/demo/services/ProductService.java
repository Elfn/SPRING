package com.pm.demo.services;

import com.pm.demo.dto.ProductDTO;
import com.pm.demo.entities.Product;
import com.pm.demo.entities.Shop;

import java.util.Set;

/**
 * Created by Elimane on Apr, 2018, at 01:37
 */
public interface ProductService {

    public Set<ProductDTO> getProducts();
    // public ProductDTO findById(Long id);
    public ProductDTO saveProduct(ProductDTO productDTO);
    public ProductDTO findProductById(Long id);
    public void deleteProduct(String id);
    public ProductDTO addOrderToProduct(String orderid,String productid);
    public double countTotalOfProducts();
    public double countTotalOfProductByStatus(String Status);
    public double findPercentageOfProductByStatus(String status);
}
