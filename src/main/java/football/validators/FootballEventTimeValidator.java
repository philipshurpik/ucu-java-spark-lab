package football.validators;

import football.models.FootballGameItem;
import football.models.GameItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

@Slf4j
@Service
public class FootballEventTimeValidator implements GameFieldValidator, Serializable {

    public static final int GAME_TIME = 90;

    @Override
    public Boolean validate(GameItem gameItem) {
        FootballGameItem footballGameItem = ((FootballGameItem) gameItem);
        Date eventTime = footballGameItem.getEventTime();
        if (eventTime.getHours() * 60 + eventTime.getMinutes() < GAME_TIME) {
            return true;
        }
        log.warn("Do not passed event time validation, event time: " + footballGameItem.getEventTime());
        return false;
    }
}
