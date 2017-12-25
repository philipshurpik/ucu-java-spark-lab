package football.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Component
public class ColumnsConfig implements Serializable {
    public List<String> columns;

    @Value("${columnNames}")
    private void setGarbage(String[] columns) {
        this.columns = Arrays.asList(columns);
    }
}
