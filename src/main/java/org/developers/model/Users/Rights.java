package org.developers.model.Users;

import lombok.Getter;

//Миронов - Внедрение ролей{
//основные права
public enum Rights {
    //общие права
    LOGIN("вход в систему"),
    CREATE_DOCUMENT("создание документа"),
    SAVE_DOCUMENT("сохранение документа"),
    WRITE_DOCUMENT("проводка документа"),
    CANCELED_WRITE_OF_DOCUMENT("отмена проводки документа"),
    DELETE_DOCUMENT("удаление документа");

    @Getter
    private String abbr;

    Rights(String abbr) {
        this.abbr = abbr;
    }
}
//Миронов - Внедрение ролей}
