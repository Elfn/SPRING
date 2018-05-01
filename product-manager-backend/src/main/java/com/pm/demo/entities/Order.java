package com.pm.demo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Elimane on Apr, 2018, at 00:27
 */

@Data
@EqualsAndHashCode(exclude = {"product"})
@Entity
@Table(name = "APPORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //@DateTimeFormat(pattern = )
    private LocalDate dateoforder;

    @ManyToOne
    private Product product;

}
