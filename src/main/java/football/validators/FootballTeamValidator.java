package football.validators;

import football.utils.AutowiredBroadcast;
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

        String fromTeam = this.teamsConfig.value().players.getOrDefault(fromPlayer, "");
        String toTeam = this.teamsConfig.value().players.getOrDefault(toPlayer, "");

        if (!fromPlayer.equals("") && !toTeam.equals("") && !fromTeam.equals(toTeam)) {
            log.warn("Do not passed same team validation\n" + gameItem);
            return false;
        }
        if (!fromPlayer.equals("") && fromTeam.equals("")) {
            log.warn("Do not passed player existence validation, player: " + fromPlayer);
            return false;
        }
        if (!toPlayer.equals("") && toTeam.equals("")) {
            log.warn("Do not passed player existence validation, player: " + toPlayer);
            return false;
        }
        return true;
    }
}
