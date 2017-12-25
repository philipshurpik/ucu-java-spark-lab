package football.models;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@Builder
public class FootballGameItem implements GameItem, Serializable {
    private int code;
    private String from;
    private String to;
    private String stadium;
    private Date eventTime;
    private Date startTime;
}
