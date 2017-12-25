package football.services;

import football.models.GameItem;
import org.apache.spark.api.java.JavaRDD;

public interface GameLoader {
    JavaRDD<GameItem> load();
}
