package org.developers.model.Product.impl;

import lombok.Getter;

public enum TypeOfBinding {
    EMPTY("переплёт не определён"),
    HARD("твёрдый");

    @Getter
    private String type;

    TypeOfBinding(String type) {
        this.type = type;
    }
}
