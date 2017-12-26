package football.enrichers;

import football.config.TeamsConfig;
import football.utils.AutowiredBroadcast;
import football.utils.RegisterUDF2;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.api.java.UDF2;

import java.util.Map;

@RegisterUDF2
public class TeamEnricher implements UDF2<String, String, String> {

    @AutowiredBroadcast
    private Broadcast<TeamsConfig> teamsConfig;

    @Override
    public String call(String playerFrom, String playerTo) throws Exception {
        Map<String, String> players = this.teamsConfig.value().players;
        return players.getOrDefault(!playerFrom.equals("") ? playerFrom : playerTo, "");
    }
}