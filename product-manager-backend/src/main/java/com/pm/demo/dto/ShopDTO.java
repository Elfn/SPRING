package com.pm.demo.dto;

import com.pm.demo.entities.Location;
import com.pm.demo.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Elimane on Apr, 2018, at 04:13
 */

@Getter
@Setter
@NoArgsConstructor
public class ShopDTO {


    private  Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    private Location location;


    private Set<ProductDTO> products;

}
