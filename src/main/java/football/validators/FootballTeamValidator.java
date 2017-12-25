package football.validators;

import football.bpp.AutowiredBroadcast;
import football.config.TeamsConfig;
import football.models.FootballGameItem;
import football.models.GameItem;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.broadcast.Broadcast;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Slf4j
@Service
public class FootballTeamValidator implements GameFieldValidator, Serializable {

    @AutowiredBroadcast
    private Broadcast<TeamsConfig> teamsConfig;

    @Override
    public Boolean validate(GameItem gameItem) {
        FootballGameItem footballGameItem = ((FootballGameItem) gameItem);
        String fromPlayer = footballGameItem.getFrom();
        String toPlayer = footballGameItem.getTo();

        if (fromPlayer != null && !this.teamsConfig.value().allPlayers.contains(fromPlayer)) {
            log.warn("Game item" + gameItem + "do not passed player existence validation, player:", fromPlayer);
            return false;
        }
        if (toPlayer != null && !this.teamsConfig.value().allPlayers.contains(toPlayer)) {
            log.warn("Game item" + gameItem + "do not passed player existence validation, player:", toPlayer);
            return false;
        }
        return true;
    }
}
