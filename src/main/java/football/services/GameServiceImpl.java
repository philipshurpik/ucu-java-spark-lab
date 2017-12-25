package football.services;

import football.models.GameItem;
import org.apache.spark.api.java.JavaRDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameLoader gameLoader;

    @Override
    public void process() {
        //Dataset<Row> dataFrame = gameLoader.load();
        JavaRDD<GameItem> rdd = gameLoader.load();
        System.out.println("numberOfLines = " + rdd.count());

        System.out.println("GameItem loader finished");
        //dataFrame.show();
        //System.out.println("DatasetLoader finished");
    }
}
