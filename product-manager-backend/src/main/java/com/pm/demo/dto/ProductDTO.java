package com.pm.demo.dto;

import com.pm.demo.entities.Category;
import com.pm.demo.entities.Order;
import com.pm.demo.entities.Product;
import com.pm.demo.entities.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Elimane on Apr, 2018, at 02:21
 */

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @NotBlank
    private double price;

    private Status status;

    @NotBlank
    private String description;


    private Byte[] image ;


    private Category category;

    private Set<OrderDTO> orders = new HashSet<>();

    private Set<ShopDTO> shops = new HashSet<>();


}
