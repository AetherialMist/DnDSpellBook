package com.github.aetherialmist.dnd.core.spells;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
public class SpellRange implements Serializable {

    public static final long serialVersionUID = 1L;

    private final int id;
    private final String value;

    @Override
    public String toString() {
        return value;
    }

}
