package com.github.aetherialmist.dnd.core;

/**
 * To be thrown if there is an error interacting with a {@link SpellDatabase}.
 */
public class SpellDataException extends Exception {

    public SpellDataException(String reason) {
        super(reason);
    }

    public SpellDataException(String reason, Throwable cause) {
        super(reason, cause);
    }

}
