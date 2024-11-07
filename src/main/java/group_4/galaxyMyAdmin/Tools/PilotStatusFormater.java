package group_4.galaxyMyAdmin.Tools;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

import group_4.galaxyMyAdmin.Enumerations.PilotStatus;

@Component
public class PilotStatusFormater implements Converter <String, PilotStatus>{

    @Override
    @Nullable
    public PilotStatus convert(@NonNull String source) {
        for(PilotStatus status : PilotStatus.values()) {
            if(status.toString().equals(source)) {
                return status;
            }
        }
        return null;
    }

}
