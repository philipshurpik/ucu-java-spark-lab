package football.services;

import football.models.FootballGameItem;
import football.models.GameItem;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;

@Service
public class FootballItemBuilder implements GameItemBuilder, Serializable {

    @Override
    public GameItem build(Map<String, String> line) {
        System.out.println("line build");
        return FootballGameItem.builder().code(1).build();
    }
}
