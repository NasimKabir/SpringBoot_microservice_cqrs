package com.spring.cqrs.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = -2373578151904440373L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeId;

    @Column(unique = true)
    private String uid;

    private String name;

    private String address;

    private String role;
}