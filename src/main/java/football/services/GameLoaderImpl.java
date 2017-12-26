package football.services;

import football.models.*;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GameLoaderImpl implements GameLoader {
    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private GameLineParser gameLineParser;

    @Autowired
    private GameItemBuilder gameItemBuilder;

    @Override
    public JavaRDD<GameItem> load() {
        JavaRDD<String> rdd = sc.textFile(sc.getConf().get("dataPath"));
        return rdd
                .filter(line -> line.trim().length() > 0)
                .map(gameLineParser::parse)
                .map(gameItemBuilder::build)
                .filter(Objects::nonNull);
    }
}