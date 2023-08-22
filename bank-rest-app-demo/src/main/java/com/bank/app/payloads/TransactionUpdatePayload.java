package com.bank.app.payloads;
import lombok.Data;


@Data
public class TransactionUpdatePayload {
   private int id;
   private String newUpdate;
}
