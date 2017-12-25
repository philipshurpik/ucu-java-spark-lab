package football.services;

import football.models.GameItem;

import java.util.Map;

public interface GameItemBuilder {
    GameItem build(Map<String, String> line);
}
