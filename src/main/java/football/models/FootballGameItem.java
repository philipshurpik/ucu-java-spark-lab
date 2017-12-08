package football.models;

import lombok.Data;

@Data
public class FootballGameItem implements GameItem {
    private int code;
    private String from;
    private String to;
    private String stadium;
    private String eventTime;
    private String startTime;

    public FootballGameItem(String[] data) {
        System.out.println("initialized," + data[0]);
    }
}
