package football.services;

import football.models.FootballGameItem;
import football.models.GameItem;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FootballItemBuilder implements GameItemBuilder, Serializable {
    private SimpleDateFormat formatEvent = new SimpleDateFormat("mm:ss");
    private SimpleDateFormat formatStart = new SimpleDateFormat("hh:mm");

    @Override
    public GameItem build(Map<String, String> line) {
        Date eventTime;
        Date startTime;
        Integer code;

        try {
            code = Integer.parseInt(line.getOrDefault("code", ""));
        }
        catch (NumberFormatException exc) {
            log.warn("Invalid code in game item line: " + line + "\n" + exc.getMessage());
            return null;
        }

        try {
            eventTime = formatEvent.parse(line.getOrDefault("eventTime", ""));
            startTime = formatStart.parse(line.getOrDefault("startTime", ""));
        }
        catch (ParseException exc) {
            log.warn("Invalid date in game item line: " + line + "\n" + exc.getMessage());
            return null;
        }

        return FootballGameItem.builder()
                .code(code)
                .from(line.get("from"))
                .to(line.get("to"))
                .stadium(line.get("stadium"))
                .eventTime(eventTime)
                .startTime(startTime)
                .build();
    }
}

//columnNames = code,from,to,eventTime,stadium,startTime