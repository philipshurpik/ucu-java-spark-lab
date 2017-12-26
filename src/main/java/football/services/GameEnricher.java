package football.services;

import football.models.GameItem;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public interface GameEnricher {
    Dataset<Row> enrich(JavaRDD<GameItem> rdd);
}
