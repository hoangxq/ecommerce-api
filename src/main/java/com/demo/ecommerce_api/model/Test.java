package com.demo.ecommerce_api.model;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class Test extends AbstractModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
