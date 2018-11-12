package com.example.cardCore.exceptions;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(Long id) {
        super("Could not find any card with id : " + id + " ! :'(");
    }
}
