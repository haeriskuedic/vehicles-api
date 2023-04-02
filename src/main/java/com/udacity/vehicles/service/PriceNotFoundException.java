package com.udacity.vehicles.service;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException() {
        super("Price not found");
    }
}
