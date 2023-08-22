package com.bank.app.controllers;

import com.bank.app.dao.BankCustomer;
import com.bank.app.payloads.AppResponsePayload;
import com.bank.app.payloads.CustomerDetailsUpdateRequestPayload;
import com.bank.app.payloads.TransactionRequestPayload;
import com.bank.app.payloads.TransactionUpdatePayload;
import com.bank.app.services.BankCustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class BankCustomerController {

    @Autowired
    private BankCustomerServices bankCustomerServices;

    @GetMapping(value = "/list")
    public AppResponsePayload sayHello(HttpServletResponse response){
        List<BankCustomer> bankCustomerList = bankCustomerServices.getAllBankCustomer();

        //Creating response payload
        AppResponsePayload responsePayload = new AppResponsePayload();
        responsePayload.setData(bankCustomerList);
        responsePayload.setStatus(HttpServletResponse.SC_OK);
        responsePayload.setMessage("Ok");

        response.setStatus(responsePayload.getStatus());

        return responsePayload;
    }

    @GetMapping(value = "/get/{id}")
    public AppResponsePayload getCustomerById(@PathVariable(name = "id") int id,
                                              HttpServletResponse response){
        BankCustomer bankCustomer = bankCustomerServices.getBankCustomerById(id);

        AppResponsePayload responsePayload = new AppResponsePayload();
        responsePayload.setData(bankCustomer);
        responsePayload.setStatus(HttpServletResponse.SC_OK);
        responsePayload.setMessage("Ok");

        response.setStatus(responsePayload.getStatus());

        return responsePayload;
    }

    @PostMapping(value = "/withdraw",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponsePayload withdrawAmount(@RequestBody TransactionRequestPayload payload,
                                             HttpServletResponse response){
        AppResponsePayload responsePayload = new AppResponsePayload();
        try{
            int amount = bankCustomerServices.withdrawAmount(payload.getId(), payload.getAmount());
            responsePayload.setData(amount);
            responsePayload.setStatus(HttpServletResponse.SC_OK);
            responsePayload.setMessage("Ok");

            response.setStatus(responsePayload.getStatus());
        }
        catch (Exception e){
            responsePayload.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responsePayload.setMessage(e.getMessage());
            responsePayload.setData(0);
        }
        return responsePayload;
    }

    @PostMapping(value="/deposit",
    consumes =MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponsePayload depositAmount(@RequestBody TransactionRequestPayload payload,HttpServletResponse response){
        AppResponsePayload responsePayload=new AppResponsePayload();
        try{
            int amount=bankCustomerServices.depositAmount(payload.getId(),payload.getAmount());
            responsePayload.setData(amount);
            responsePayload.setStatus(HttpServletResponse.SC_OK);
            responsePayload.setMessage("OK");

            response.setStatus(responsePayload.getStatus());
        }
        catch(Exception e){
            responsePayload.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responsePayload.setMessage(e.getMessage());
            responsePayload.setData(0);
        }
        return responsePayload;
    }
    @PostMapping(value="/updatePassword",
    consumes=MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponsePayload updatePassword(@RequestBody TransactionUpdatePayload payload, HttpServletResponse response){
        AppResponsePayload responsePayload=new AppResponsePayload();
        try{
            String check= bankCustomerServices.updatePassword(payload.getId(), Integer.parseInt(payload.getNewUpdate()));
            responsePayload.setData(check);
            responsePayload.setStatus(HttpServletResponse.SC_OK);
            responsePayload.setMessage("OK");

            response.setStatus(responsePayload.getStatus());
        }
        catch(Exception e){
            responsePayload.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responsePayload.setMessage(e.getMessage());
            responsePayload.setData(0);
        }
        return responsePayload;
    }

    @PostMapping(value="/updateName",
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponsePayload updateName(@RequestBody TransactionUpdatePayload payload, HttpServletResponse response){
        AppResponsePayload responsePayload=new AppResponsePayload();
        try{
            String check= bankCustomerServices.updatePassword(payload.getId(), Integer.parseInt(payload.getNewUpdate()));
            responsePayload.setData(check);
            responsePayload.setStatus(HttpServletResponse.SC_OK);
            responsePayload.setMessage("OK");

            response.setStatus(responsePayload.getStatus());
        }
        catch(Exception e){
            responsePayload.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responsePayload.setMessage(e.getMessage());
            responsePayload.setData(0);
        }
        return responsePayload;
    }

    @PostMapping(value = "/update",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponsePayload updateCustomerDetails(@RequestBody CustomerDetailsUpdateRequestPayload requestPayload,
                                                    HttpServletResponse response){
        AppResponsePayload responsePayload = new AppResponsePayload();
        try{
            BankCustomer bankCustomer = bankCustomerServices.getBankCustomerById(requestPayload.getId());
            bankCustomer.setCustomerName(requestPayload.getCustomerName());
            bankCustomer.setCustomerPassword(requestPayload.getCustomerPassword());
            bankCustomer = bankCustomerServices.updateBankCustomer(bankCustomer);
            responsePayload.setData(bankCustomer);
            responsePayload.setMessage("ok");
            responsePayload.setStatus(HttpServletResponse.SC_OK);
        }catch (Exception e){
            responsePayload.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responsePayload.setMessage(e.getMessage());
        }
        return responsePayload;
    }
}
