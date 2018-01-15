package org.developers.system;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

public class LogOfSystem {
    public static void writeLog(LogType type, String message) {
        //todo: определить каталог приложения
        try (FileWriter fw = new FileWriter("M:\\base\\logs")) {
            Optional<LogType> typeOptional = Optional.of(type);
            Optional<String> messageOptional = Optional.of(message);
            fw.write(LocalDateTime.now() + "\t" + typeOptional.orElse(LogType.EMPTY) + "\t" + messageOptional.orElse("сообщений нет") + "\n");
            fw.flush();
        } catch (IOException ex) {
            System.out.println("Не удалось запистаь файл лога");
        }
    }
}
