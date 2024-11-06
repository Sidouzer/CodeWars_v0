package group_4.galaxyMyAdmin.Tools;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import group_4.galaxyMyAdmin.Enumerations.Race;

@Component
public class RaceFormater implements Converter <String, Race> {

    @Override
    @Nullable
    public Race convert(@NonNull String fromForm) {
        for(Race race : Race.values()) {
            if(race.toString().equals(fromForm)) {
                return race;
            }
        }
        return null;
    }


}
