package org.developers.model.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

//Миронов - Внедрение ролей{
//Типичный пользователь
//todo: необходима доработка
@AllArgsConstructor
public class Role {
    //имя пользователя
    @Getter
    private String name;

    //права пользователя
    @Getter
    private LinkedHashMap<Rights, Boolean> rights;

    //изменение прав
    public void diffRights(Rights right, boolean resolution) {

        Optional<Rights> rightOptional = Optional.of(right);

        for (Map.Entry<Rights, Boolean> pointOfMapOfRights : rights.entrySet()) {
            if (rightOptional.orElse(Rights.LOGIN).equals(pointOfMapOfRights.getKey())) {
                rights.put(rightOptional.orElse(Rights.LOGIN), resolution);
            }
        }
    }
}
//Миронов - Внедрение ролей}
