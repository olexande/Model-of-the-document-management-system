package org.developers.model;

import lombok.Getter;

public enum TypeOfDocument {
    INVENTORY("инвентаризация"),
    CONTRACT_WITH_SUPPLIERS("контракт с поставщиками"),
    ORDER_TO_SUPPLIERS("контракт постамвщикам"),
    INVOICE("счёт-фактура"),
    TIMETABLE("табель"),
    ENCASHMENT("инкассация"),
    FINANCE_MOVEMENT("движенеи финансов"),
    SETTLEMENT_SHEET("рассчётный лист");

    @Getter
    private String title;

    TypeOfDocument(String title) {
        this.title = title;
    }
}
