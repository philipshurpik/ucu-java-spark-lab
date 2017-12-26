package football.validators;

import football.utils.AutowiredBroadcast;
import football.config.CodesConfig;
import football.models.FootballGameItem;
import football.models.GameItem;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.broadcast.Broadcast;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Slf4j
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
        Boolean isValid = false;

        if (description != null) {
            if (codesConfig.multiplePlayerActions.contains(description)) {
                isValid = !footballGameItem.getFrom().equals("") && !footballGameItem.getTo().equals("");
            }
            else if (codesConfig.fromPlayerActions.contains(description)) {
                isValid = !footballGameItem.getFrom().equals("") && footballGameItem.getTo().equals("");
            }
            else if (codesConfig.toPlayerActions.contains(description)) {
                isValid = footballGameItem.getFrom().equals("") && !footballGameItem.getTo().equals("");
            }
        }
        if (!isValid) {
            log.warn("Game item" + gameItem + "do not passed code validation, code:", footballGameItem.getCode());
        }
        return isValid;
    }
}
