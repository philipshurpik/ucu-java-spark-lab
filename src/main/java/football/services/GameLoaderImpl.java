package football.services;

import football.models.*;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameLoaderImpl implements GameLoader {
    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private GameItemBuilder gameItemBuilder;

    @Autowired
    private GameLineParser gameLineParser;

    @Override
    public JavaRDD<GameItem> load() {
        JavaRDD<String> rdd = sc.textFile("data/footballData.txt");
        return rdd
                .map(gameLineParser::parse)
                .map(gameItemBuilder::build);
    }
}