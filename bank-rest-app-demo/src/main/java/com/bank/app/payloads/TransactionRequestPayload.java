package com.bank.app.payloads;

import lombok.Data;

@Data
public class TransactionRequestPayload {
    private int id;
    private int amount;
}
