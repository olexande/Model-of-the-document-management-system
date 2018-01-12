package org.developers.business_logic;

import java.io.File;
import java.util.Optional;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//Миронов - настройка инициализации
//первоначальная инициализация
public class Initialization {

    private static File home;

    public static void init(String path) {
        //получаем сведения о ОС
        Properties properties = System.getProperties();
        //пишем reg для Windows
        Pattern windowsPattern = Pattern.compile("(Windows)(.)*");
        //пишем reg для Linux
        Pattern linuxPattern = Pattern.compile("(Linux)(.)*");

        Matcher eq_windows = windowsPattern.matcher(properties.get("os.name").toString());
        Matcher eq_linux = linuxPattern.matcher(properties.get("os.name").toString());

        Optional<String> pathOfSystem = Optional.of(path);

        if (eq_windows.matches()) {          //если ОС - Windows
            home = new File(pathOfSystem.orElse("C://"));
        } else if (eq_linux.matches()) {     //если ОС - Linux
            home = new File(pathOfSystem.orElse("//home//" + properties.get("user.name") + "//"));
        }

        //ищеи папку "base"
        boolean fact = false;
        for (File file : home.listFiles()) {
            if (file.getName().equals("base") && file.isDirectory()) {
                fact = true;
                break;
            }
        }

        //если папка "base" отсутствует - создаём её, и систему каталогов внутри неё
        if (!fact) {
            boolean createFolderBase = new File(path + "//base//").mkdir();
        }

        //папка с документами
        boolean createFolderDocuments = new File(path + "//base//documents//").mkdir();
        //todo: при желании - раскомментировать
        //System.out.println(createFolderDocuments ? "Папка \"documents\" создана" : "Папка \"documents\" не создана");
    }
}
