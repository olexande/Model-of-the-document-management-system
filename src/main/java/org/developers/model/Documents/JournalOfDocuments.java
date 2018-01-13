package org.developers.model.Documents;

//Миронов - Внедрение  документов и отчётов{
//Журнал документов
public interface JournalOfDocuments {
    //название журнала
    String getTitle();

    //тип документов
    TypeOfDocument getType();

    //получение документов
    Document getDocument();
}
//Миронов - Внедрение  документов и отчётов}