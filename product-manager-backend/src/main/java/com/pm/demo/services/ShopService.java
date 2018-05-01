package com.pm.demo.services;

import com.pm.demo.dto.ShopDTO;

import java.util.Set;

/**
 * Created by Elimane on Apr, 2018, at 01:37
 */
public interface ShopService {


    public Set<ShopDTO> getShops();
    public ShopDTO saveShop(ShopDTO shopDTO);
    public ShopDTO findShopById(Long id);
    public void deleteShop(String id);


}
