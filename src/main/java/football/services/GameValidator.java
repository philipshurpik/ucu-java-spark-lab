package football.services;

import football.models.GameItem;
import org.apache.spark.api.java.JavaRDD;

public interface GameValidator {
    JavaRDD<GameItem> validate(JavaRDD<GameItem> rdd);
}
