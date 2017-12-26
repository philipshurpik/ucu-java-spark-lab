package football.services;

import football.aspects.ShowDataFrameAtTheEnd;
import football.models.GameItem;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.storage.StorageLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameLoader gameLoader;

    @Autowired
    private GameValidator gameValidator;

    @Autowired
    private GameEnricher gameEnricher;

    @Override
    @ShowDataFrameAtTheEnd()
    public void process() {
        JavaRDD<GameItem> rdd = gameLoader.load();
        rdd.persist(StorageLevel.MEMORY_AND_DISK());
        System.out.println("number of lines before validation = " + rdd.count());

        rdd = gameValidator.validate(rdd);
        rdd.persist(StorageLevel.MEMORY_AND_DISK());
        System.out.println("number of lines after validation = " + rdd.count());

        Dataset<Row> dataFrame = gameEnricher.enrich(rdd);
        System.out.println("Total lines: " + dataFrame.count());
    }
}
