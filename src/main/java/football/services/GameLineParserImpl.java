package football.services;

import football.utils.AutowiredBroadcast;
import football.config.ColumnsConfig;
import org.apache.spark.broadcast.Broadcast;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Service
public class GameLineParserImpl implements GameLineParser, Serializable {

    @AutowiredBroadcast
    private Broadcast<ColumnsConfig> columnsConfig;

    @Override
    public Map<String, String> parse(String rawLine) {
        Map<String, String> line = new HashMap<>();
        String[] data = rawLine.split(";");
        for (String item : data) {
            if (item.contains("=")) {
                String[] keyValue = item.split("=");
                if (this.columnsConfig.value().columns.contains(keyValue[0])) {
                    line.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return line;
    }
}
