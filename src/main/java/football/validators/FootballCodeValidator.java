package football.validators;

import football.bpp.AutowiredBroadcast;
import football.config.CodesConfig;
import football.models.FootballGameItem;
import football.models.GameItem;
import org.apache.spark.broadcast.Broadcast;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class FootballCodeValidator implements GameFieldValidator, Serializable {

    @AutowiredBroadcast
    private Broadcast<CodesConfig> codesConfig;

    @Override
    public Boolean validate(GameItem gameItem) {
        FootballGameItem footballGameItem = ((FootballGameItem) gameItem);
        CodesConfig codesConfig = this.codesConfig.value();
        Integer code = footballGameItem.getCode();
        String description = codesConfig.codes.get(code.toString());
        if (description != null) {
            if (codesConfig.multiplePlayerActions.contains(description)) {
                return !footballGameItem.getFrom().equals("") && !footballGameItem.getTo().equals("");
            }
            else if (codesConfig.fromPlayerActions.contains(description)) {
                return !footballGameItem.getFrom().equals("") && footballGameItem.getTo().equals("");
            }
            else if (codesConfig.toPlayerActions.contains(description)) {
                return footballGameItem.getFrom().equals("") && !footballGameItem.getTo().equals("");
            }
        }
        return false;
    }
}
