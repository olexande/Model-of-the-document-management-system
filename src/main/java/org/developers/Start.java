package org.developers;

import org.developers.business_logic.Initialization;

public class Start {
    public static void main(String[] args) {
        //todo: определить метоположение основного тома/каталога
        //todo: обязательный блок
        String pathToBaseLocation = "M://";
        Initialization.init(pathToBaseLocation);
    }
}
