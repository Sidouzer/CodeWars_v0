package group_4.galaxyMyAdmin.Tools;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

import group_4.galaxyMyAdmin.Enumerations.ShipStatus;

@Component
public class ShipStatusFormater implements Converter <String, ShipStatus> {

    @Override
    @Nullable
    public ShipStatus convert(@NonNull String source) {
        for(ShipStatus status : ShipStatus.values()) {
            if(status.toString().equals(source)) {
                return status;
            }
        }
        return null;
    }

}
