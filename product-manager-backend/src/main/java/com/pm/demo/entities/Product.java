package com.pm.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Elimane on Apr, 2018, at 00:27
 */

@Getter
@Setter
@Entity
//@Table(name = "APPPRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private Status status;
    private String description;


    @Lob
    private Byte[] image ;


    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "product_shop",joinColumns = @JoinColumn(name="product_id"),inverseJoinColumns = @JoinColumn(name="shop_id"))
    private Set<Shop> shops = new HashSet<>();




    //To make relationship between product and order during persistence
    public Product addOrder(Order order){
        order.setProduct(this);
        orders.add(order);
        return this;
    }


//    @Override
//    public String toString() {
//        return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
//    }



}
