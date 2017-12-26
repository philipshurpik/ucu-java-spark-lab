package football.config;

import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@Component
public class TeamsConfig implements Serializable {
    private static final String PROPERTY_SOURCE = "teams.properties";

    public Map<String, String> players = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        Properties properties = PropertiesLoaderUtils.loadAllProperties(PROPERTY_SOURCE);
        properties.forEach((key, value) -> {
            String[] members = ((String) value).split(",");
            Arrays.asList(members).forEach(player -> {
                if (player.trim().length() > 0) {
                    players.put(player, (String) key);
                }
            });
        });
    }
}
