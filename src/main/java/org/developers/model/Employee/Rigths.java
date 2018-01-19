package org.developers.model.Employee;

//права
public enum Rigths {
    //перечень
    //attention: необходжимо дополнить!
    LOGIN("вход в систему"),                                            //вход в систему
    CREATE_DOCUMENT("создание документа"),                              //создание документа
    SAVE_DOCUMENT("сохранение документа"),                              //сохранение документа
    WRITE_DOCUMENT("проведение документа"),                             //проведение документа
    CANCELLATION_OF_THE_DOCUMENT_WIRING("отмена првоодки документа"),   //отмена проводки документа
    DELETE_DOCUMENT("удаление документа");                              //удаление документа

    private String abbr;

    Rigths(String abbr) {
        this.abbr = abbr;
    }
}
