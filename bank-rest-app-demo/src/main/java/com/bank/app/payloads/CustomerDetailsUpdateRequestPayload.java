package com.bank.app.payloads;

import lombok.Data;

@Data
public class CustomerDetailsUpdateRequestPayload {
    private int id;
    private String customerName;
    private int customerPassword;
}
