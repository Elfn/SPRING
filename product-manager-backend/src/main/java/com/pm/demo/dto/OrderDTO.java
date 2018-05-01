package com.pm.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Created by Elimane on Apr, 2018, at 04:12
 */

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @NotBlank
    private LocalDate dateoforder;


    private Long productID;
}
