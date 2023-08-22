package com.bank.app.services;

import com.bank.app.dao.BankCustomer;
import com.bank.app.repositories.BankCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class BankCustomerServices {

    @Autowired
    private BankCustomerRepository bankCustomerRepository;

    public List<BankCustomer> getAllBankCustomer(){
        return bankCustomerRepository.getAllBankCustomer();
    }

    public BankCustomer getBankCustomerById(int id){
        return bankCustomerRepository.getBankCustomerById(id);
    }

    public int withdrawAmount(int id, int amount){
        BankCustomer customer = bankCustomerRepository.getBankCustomerById(id);
        if(amount > customer.getBalance()){
            return 0;
        }
        else{
            customer.setBalance(customer.getBalance() - amount);
            bankCustomerRepository.save(customer);
            return amount;
        }
    }

    public int depositAmount(int id, int amount){
        BankCustomer customer = bankCustomerRepository.getBankCustomerById(id);
        if(amount <= 0){
            return 0;
        }
        else{
            customer.setBalance(customer.getBalance() + amount);
            bankCustomerRepository.save(customer);
            return amount;
        }
    }
    public String updatePassword(int id, int newPassword){
        BankCustomer customer=bankCustomerRepository.getBankCustomerById(id);
        customer.setCustomerPassword(newPassword);
        bankCustomerRepository.save(customer);
        return "Password Updated";
    }
    public String updateName(int id,String newName){
        BankCustomer customer=bankCustomerRepository.getBankCustomerById(id);
        customer.setCustomerName(newName);
        bankCustomerRepository.save(customer);
        return "Name Updated";
    }

    public BankCustomer updateBankCustomer(BankCustomer bankCustomer){
        return bankCustomerRepository.save(bankCustomer);
    }
}
