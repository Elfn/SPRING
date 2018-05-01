package com.pm.demo.converters;

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
 * Created by Elimane on Apr, 2018, at 02:20
 */
@Component
public class ProductConverter implements Converter<Product,ProductDTO> {

    private final OrderConverter orderConverter;

    public ProductConverter(OrderConverter orderConverter) {
        this.orderConverter = orderConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public ProductDTO convert(Product source) {

        if (source == null) {
            return null;
        }

        final ProductDTO dto = new ProductDTO();

        dto.setId(source.getId());
        dto.setName(source.getName());
        dto.setPrice(source.getPrice());
        dto.setStatus(source.getStatus());
        dto.setImage(source.getImage());
        dto.setCategory(source.getCategory());
        dto.setDescription(source.getDescription());

        if (source.getOrders() != null && source.getOrders().size() > 0){
            source.getOrders()
                    .forEach((Order order) -> dto.getOrders().add(orderConverter.convert(order)));
        }



        return dto;
    }


    public ProductDTO userToUserDTO(Product product) {
        return this.convert(product);
    }


    public Set<ProductDTO> productsToProductDTOs(Set<Product> products) {
        return products.stream()
                .filter(Objects::nonNull)
                .map(this::userToUserDTO)
                .collect(Collectors.toSet());
    }




}
