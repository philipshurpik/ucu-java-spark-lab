package football.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Component
@PropertySource("classpath:football_columns.properties")
public class ColumnsConfig implements Serializable {
    public List<String> columns;

    @Value("${columnNames}")
    private void setColumns(String[] columns) {
        this.columns = Arrays.asList(columns);
    }
}
