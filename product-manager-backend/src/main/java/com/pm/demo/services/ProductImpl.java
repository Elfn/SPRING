package com.pm.demo.services;

import com.pm.demo.converters.OrderConverter;
import com.pm.demo.converters.OrderDTOConverter;
import com.pm.demo.converters.ProductConverter;
import com.pm.demo.converters.ProductDTOConverter;
import com.pm.demo.dto.OrderDTO;
import com.pm.demo.dto.ProductDTO;
import com.pm.demo.entities.Order;
import com.pm.demo.entities.Product;
import com.pm.demo.entities.Shop;
import com.pm.demo.repositories.OrderRepository;
import com.pm.demo.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Elimane on Apr, 2018, at 01:37
 */
@Slf4j
@Service
public class ProductImpl implements ProductService{

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    private final ProductDTOConverter productDTOConverter;
    private  final ProductConverter productConverter;

    private final OrderConverter orderConverter;
    private final OrderDTOConverter orderDTOConverter;


    public ProductImpl(ProductRepository productRepository, OrderRepository orderRepository, ProductDTOConverter productDTOConverter, ProductConverter productConverter, OrderConverter orderConverter, OrderDTOConverter orderDTOConverter) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.productDTOConverter = productDTOConverter;
        this.productConverter = productConverter;
        this.orderConverter = orderConverter;
        this.orderDTOConverter = orderDTOConverter;
    }

    @Override
    public Set<ProductDTO> getProducts() {
        log.debug("I'm in the getProducts service!!!!");
        //Unique products collection
        //Set<Product> products = (Set<Product>) productRepository.findAll();

        //Convert Product set into ProductDTO set
        Set<ProductDTO> setOfProductDTOs = productConverter.productsToProductDTOs((Set<Product>) productRepository.findAll());


        return setOfProductDTOs;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product detachedProduct = productDTOConverter.convert(productDTO);

         Product savedProduct = productRepository.save(detachedProduct);

        log.debug("Saved ProductId:" + savedProduct.getId());


        //Return the DTO object of product
        return productConverter.convert(savedProduct);
    }

    @Override
    public ProductDTO findProductById(Long id) {

       Optional<Product> foundProduct = productRepository.findById(id);

       if(foundProduct.isPresent())
       {
           log.debug("Product object =>"+foundProduct.toString()+", not found by Id in Product Impl!!! ");
       }

       //Conversion
        ProductDTO productToReturn = productConverter.convert(foundProduct.get());

        return productToReturn;
    }

    @Override
    public void deleteProduct(String id) {

      productRepository.deleteById(Long.valueOf(id));

    }

    @Override
    public ProductDTO addOrderToProduct(String orderid, String productid) {


        //We need product object to get order
        Optional<Product> productOptional = productRepository.findById(Long.valueOf(productid));

        //We must check if recipe has been found
        if(!productOptional.isPresent())
        {
            //todo impl error handling
            log.error("product id not found. Id: " + productid);
        }

        //else we get recipe
        ProductDTO product = productConverter.convert(productOptional.get());




        Optional<Order> orderOptional = orderRepository.findById(Long.valueOf(orderid));


        if(!orderOptional.isPresent())
        {
            //todo impl error handling
            log.error("Order id not found. Id: " + orderid);
        }

        OrderDTO order = orderConverter.convert(orderOptional.get());


        order.setProductID(product.getId());
        product.getOrders().add(order);

        productRepository.save(productDTOConverter.convert(product));

        return  product;
    }

    @Override
    public double countTotalOfProducts() {
        return productRepository.count();
    }

    @Override
    public double countTotalOfProductByStatus(String status) {
        return productRepository.countAllByStatus(status);
    }


    @Override
    public double findPercentageOfProductByStatus(String status) {

        final double soldProducts = countTotalOfProductByStatus(status);
        final double allProducts = countTotalOfProducts();

        if(soldProducts == 0)
        {
            log.debug("No products were sold");
        }

        if(allProducts == 0)
        {
            log.debug("There are not products");
        }

        return (soldProducts/allProducts)*100;
    }


}
