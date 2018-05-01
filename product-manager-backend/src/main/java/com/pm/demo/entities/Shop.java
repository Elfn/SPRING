package com.pm.demo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Elimane on Apr, 2018, at 00:27
 */

@Data
@EqualsAndHashCode(exclude = {"product"})
@Entity
//@Table(name = "APPSHOP")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Location location;


    @ManyToMany(mappedBy = "shops")
    private Set<Product> products;

}
