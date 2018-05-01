package com.pm.demo.converters;

import com.pm.demo.dto.ProductDTO;
import com.pm.demo.dto.ShopDTO;
import com.pm.demo.entities.Product;
import com.pm.demo.entities.Shop;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Elimane on Apr, 2018, at 04:27
 */
@Component
public class ShopConverter implements Converter<Shop, ShopDTO>{

    private final ProductConverter productConverter;

    public ShopConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public ShopDTO convert(Shop source) {
        if (source == null) {
            return null;
        }

        ShopDTO dto = new ShopDTO();

        dto.setId(source.getId());
        dto.setName(source.getName());
        dto.setLocation(source.getLocation());


        if (source.getProducts() != null && source.getProducts().size() > 0){
            source.getProducts()
                    .forEach((Product product) -> dto.getProducts().add(productConverter.convert(product)));
        }

        return dto;
    }

    public ShopDTO shopToShopDTO(Shop shop) {
        return this.convert(shop);
    }


    public Set<ShopDTO> productsToProductDTOs(Set<Shop> shops) {
        return shops.stream()
                .filter(Objects::nonNull)
                .map(this::shopToShopDTO)
                .collect(Collectors.toSet());
    }
}
