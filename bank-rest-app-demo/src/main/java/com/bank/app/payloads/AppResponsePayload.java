package com.bank.app.payloads;

import lombok.Data;

@Data
public class AppResponsePayload {
    private Object data;
    private int status;
    private String message;
}
