//package com.example.demo.model;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//
//@Entity
//@NoArgsConstructor
//public class Identificator {
//
//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
//    private String id;
//
//    @Column(name = "number")
//    private Number num;
//
//    public Identificator(Number number){
//        this.num = num;
//    }
//
//
//}
