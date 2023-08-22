package com.bank.app.repositories;

import com.bank.app.dao.BankCustomer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BankCustomerRepository extends Repository<BankCustomer, Integer> {

    BankCustomer save(BankCustomer data);

    @Query("select data from BankCustomer data")
    List<BankCustomer> getAllBankCustomer();

    @Query("select data from BankCustomer data where data.id=:customerId")
    BankCustomer getBankCustomerById(@Param("customerId") int id);
}
