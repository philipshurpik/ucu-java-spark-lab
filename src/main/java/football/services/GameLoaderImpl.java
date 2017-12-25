package football.services;

import football.models.*;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameLoaderImpl implements GameLoader {
    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private GameItemBuilder gameItemBuilder;

    @Override
    public JavaRDD<GameItem> load() {
        JavaRDD<String> rdd = sc.textFile("data/footballData.txt");
        return rdd
                .map(rawLine -> {
                    Map<String, String> line = new HashMap<>();
                    String[] data = rawLine.split(";");
                    for (String item : data) {
                        if (item.contains("=")) {
                            String[] columns = data[0].split("=");
                            line.put(columns[0], columns[1]);
                        }
                    }
                    System.out.println("line" + line);
                    return line;
                })
                .map(gameItemBuilder::build);
    }
}