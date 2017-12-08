package football.models;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class FootballGameItem implements GameItem {
    private int code;
    private String from;
    private String to;
    private String stadium;
    private String eventTime;
    private String startTime;

    public void initialize(String[] data) {
        System.out.println("initialized," + data[0]);
    }
}
