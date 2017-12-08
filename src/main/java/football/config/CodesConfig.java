package football.config;

import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class CodesConfig implements Serializable {
    private static final String PROPERTY_SOURCE = "codes.properties";

    public Map<String, String> codes = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        Properties properties = PropertiesLoaderUtils.loadAllProperties(PROPERTY_SOURCE);
        properties.forEach((k,v) -> codes.put((String) k, (String)v));
    }
}
