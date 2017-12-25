package football.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@Component
@PropertySource("classpath:code_types.properties")
public class CodesConfig implements Serializable {
    private static final String PROPERTY_SOURCE = "codes.properties";

    public Map<String, String> codes = new HashMap<>();
    public List<String> multiplePlayerActions;
    public List<String> toPlayerActions;
    public List<String> fromPlayerActions;

    @Value("${multiplePlayerActions}")
    private void setMultiplePlayerActions(String[] multiplePlayerActions) {
        this.multiplePlayerActions = Arrays.asList(multiplePlayerActions);
    }

    @Value("${toPlayerActions}")
    private void setToPlayerActions(String[] toPlayerActions) {
        this.toPlayerActions = Arrays.asList(toPlayerActions);
    }

    @Value("${fromPlayerActions}")
    private void setFromPlayerActions(String[] fromPlayerActions) {
        this.fromPlayerActions = Arrays.asList(fromPlayerActions);
    }

    @PostConstruct
    public void init() throws IOException {
        Properties properties = PropertiesLoaderUtils.loadAllProperties(PROPERTY_SOURCE);
        properties.forEach((k,v) -> codes.put((String) k, (String)v));
    }
}
