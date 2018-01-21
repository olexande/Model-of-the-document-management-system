package org.developers;

import org.developers.model.Product.impl.Book;
import org.developers.model.Product.impl.TypeOfBinding;
import org.developers.system.Initialization;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {
    //rule: указание папки "установки"
    public static String pathToApplication = "M:\\";

    public static void main(String[] args) {
        //rule: здесь это должно быть всегда
        Initialization.init(pathToApplication);
        //rule: ############################


        ApplicationContext ctx = new ClassPathXmlApplicationContext("products.xml");
        Book bookWork = ctx.getBean("book", Book.class);
        bookWork.setTitleOfProduct("Идиотъ Классика");
        bookWork.setTitleOfBook("Идиотъ");
        bookWork.setAuthor("Фёдоро Михайловыич Достоевский");
        bookWork.setNumberOfPage((short) 254);
        bookWork.setPublishingHouse("Просвещение");
        bookWork.setBinding(TypeOfBinding.HARD);
        bookWork.add();
    }
}
