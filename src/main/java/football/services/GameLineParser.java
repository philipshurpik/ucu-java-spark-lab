package football.services;

import java.util.Map;

public interface GameLineParser {
    Map<String, String> parse(String line);
}
