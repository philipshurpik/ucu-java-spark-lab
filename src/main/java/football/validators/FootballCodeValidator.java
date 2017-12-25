package football.validators;

import football.bpp.AutowiredBroadcast;
import football.config.CodesConfig;
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
        return true;
    }
}
