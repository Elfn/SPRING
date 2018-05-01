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
 * Created by Elimane on Apr, 2018, at 01:33
 */

@Component
public class ShopDTOConverter implements Converter<ShopDTO,Shop> {

    final private ProductDTOConverter productDTOConverter;

    public ShopDTOConverter(ProductDTOConverter productDTOConverter) {
        this.productDTOConverter = productDTOConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Shop convert(ShopDTO source) {



        if (source == null) {
            return null;
        }

        final Shop shop = new Shop();

        shop.setId(source.getId());
        shop.setName(source.getName());
        shop.setLocation(source.getLocation());


        if (source.getProducts() != null && source.getProducts().size() > 0){
            source.getProducts()
                    .forEach((ProductDTO productDTO) -> shop.getProducts().add(productDTOConverter.convert(productDTO)));
        }

        return shop;

    }


    public Shop shopDTOToShop(ShopDTO shopDTO) {
        return this.convert(shopDTO);
    }


    public Set<Shop> productsToProductDTOs(Set<ShopDTO> shopDTOS) {
        return shopDTOS.stream()
                .filter(Objects::nonNull)
                .map(this::shopDTOToShop)
                .collect(Collectors.toSet());
    }
}
