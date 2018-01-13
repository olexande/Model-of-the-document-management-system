package org.developers.business_logic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

//Миронов - Внедрение пользователей{
//Типичный пользователь
//todo: необходима доработка
public class User {
    //имя пользователя
    @Getter
    @Setter
    private String name;

    //права пользователя
    @Getter
    @Setter
    private LinkedHashMap<Rights, Boolean> rights;

    //изменение прав
    public void diffRights(Rights right, boolean resolution) {

        Optional<Rights> rightOptional = Optional.of(right);

        boolean fact = false;
        for (Map.Entry<Rights, Boolean> rigthMap : rights.entrySet()) {
            if (rightOptional.orElse(Rights.LOGIN).equals(rigthMap.getKey())) {
                rights.put(rightOptional.orElse(Rights.LOGIN), resolution);
            }
        }
    }
}
//Миронов - Внедрение пользователей}
