package com.example.historyCore.exceptions;

public class PlayedGameNotFoundException extends RuntimeException{
    public PlayedGameNotFoundException(Long id){ super("Could not find any played game with id : " + id +" ! :'(");}
}
