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
 * Created by Elimane on Apr, 2018, at 01:28
 */

@Component
public class ProductDTOConverter implements Converter<ProductDTO,Product>{


    private final OrderDTOConverter orderDTOConverter;

    public ProductDTOConverter(OrderDTOConverter orderDTOConverter) {
        this.orderDTOConverter = orderDTOConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Product convert(ProductDTO source) {

        if (source == null) {
            return null;
        }

        final Product product = new Product();

        product.setId(source.getId());
        product.setName(source.getName());
        product.setPrice(source.getPrice());
        product.setStatus(source.getStatus());
        product.setImage(source.getImage());
        product.setCategory(source.getCategory());
        product.setDescription(source.getDescription());

        if (source.getOrders() != null && source.getOrders().size() > 0){
            source.getOrders()
                    .forEach((OrderDTO orderDTO) -> product.getOrders().add(orderDTOConverter.convert(orderDTO)));
        }



        return product;
    }


    public Product productDTOToProduct(ProductDTO dto) {
        return this.convert(dto);
    }


    public Set<Product> productsToProductDTOs(Set<ProductDTO> productsDTO) {
        return productsDTO.stream()
                .filter(Objects::nonNull)
                .map(this::productDTOToProduct)
                .collect(Collectors.toSet());
    }
}
