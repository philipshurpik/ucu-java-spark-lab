package football.models;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class FootballGameItem implements GameItem, Serializable {
    private int code;
    private String from;
    private String to;
    private String stadium;
    private String eventTime;
    private String startTime;
}
