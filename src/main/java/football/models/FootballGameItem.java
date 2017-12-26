package football.models;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


@Data
@Builder
public class FootballGameItem implements GameItem, Serializable {
    private int code;
    private String from;
    private String to;
    private String stadium;
    private Timestamp eventTime;
    private Timestamp startTime;
}
