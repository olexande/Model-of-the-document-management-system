package org.developers.system.Log;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
//класс, содержащий методы рабоыт с логами
public class LogOfSystem {
    //запись лога
    public static void writeLog(LogType type, String message) {
        //todo: определить каталог приложения
        //имя лога: текущая дата. При необходимости записи добавляются в файл
        try (FileWriter fw = new FileWriter("M:/base/logs/" + LocalDate.now() + ".log", true)) {
            Optional<LogType> typeOptional = Optional.of(type);
            Optional<String> messageOptional = Optional.of(message);
            fw.write(LocalTime.now() + "\t" + typeOptional.orElse(LogType.EMPTY) + "\t" + messageOptional.orElse("сообщений нет") + "\n");
            fw.flush();
        } catch (IOException ex) {
            System.out.println("Не удалось записать файл лога");
        }
    }
}
