package com.bank.app.dao;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class BankCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String customerName;
    @Column(name = "account_number")
    private int customerAccountNumber;
    @Column(name = "password")
    private int customerPassword;
    @Column(name="balance")
    private int balance;
}
