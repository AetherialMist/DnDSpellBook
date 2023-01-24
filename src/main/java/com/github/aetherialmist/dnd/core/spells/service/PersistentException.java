package com.github.aetherialmist.dnd.core.spells.service;

public class PersistentException extends Exception {

    public PersistentException(String message) {
        super(message);
    }

    public PersistentException(String message, Throwable cause) {
        super(message, cause);
    }

}
