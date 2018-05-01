package com.pm.demo.services;

import com.pm.demo.converters.ShopConverter;
import com.pm.demo.converters.ShopDTOConverter;
import com.pm.demo.dto.ShopDTO;
import com.pm.demo.entities.Product;
import com.pm.demo.entities.Shop;
import com.pm.demo.repositories.ShopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Elimane on Apr, 2018, at 01:38
 */
@Slf4j
@Service
public class ShopImpl implements ShopService{

    final private ShopRepository shopRepository;
    final private ShopConverter shopConverter;
    final  private ShopDTOConverter shopDTOConverter;

    public ShopImpl(ShopRepository shopRepository, ShopConverter shopConverter, ShopDTOConverter shopDTOConverter) {
        this.shopRepository = shopRepository;
        this.shopConverter = shopConverter;
        this.shopDTOConverter = shopDTOConverter;
    }

    @Override
    public Set<ShopDTO> getShops() {
        log.debug("I'm in the getShops service!!!!");

        Set<ShopDTO> setOfShopDTOs = shopConverter.productsToProductDTOs((Set<Shop>) shopRepository.findAll());

        return setOfShopDTOs;
    }

    @Override
    public ShopDTO saveShop(ShopDTO shopDTO) {

       Shop detachedShop = shopDTOConverter.convert(shopDTO);

        Shop savedShop = shopRepository.save(detachedShop);

        log.debug("Saved ShopId:" + savedShop.getId());


        //Return the DTO object of product
        return shopConverter.convert(savedShop);
    }

    @Override
    public ShopDTO findShopById(Long id) {


        Optional<Shop> foundShop = shopRepository.findById(id);

        if(foundShop.isPresent())
        {
            log.debug("Shop object =>"+foundShop.toString()+", not found by Id in Shop Impl!!! ");
        }

        //Conversion
        ShopDTO shopToReturn = shopConverter.convert(foundShop.get());

        return shopToReturn;

    }

    @Override
    public void deleteShop(String id) {

        shopRepository.deleteById(Long.valueOf(id));

    }
}
