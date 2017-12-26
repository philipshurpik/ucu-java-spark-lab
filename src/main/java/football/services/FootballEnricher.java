package football.services;

import football.models.FootballGameItem;
import football.models.GameItem;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FootballEnricher implements GameEnricher {

    @Autowired
    private SQLContext sqlContext;

    @Override
    public Dataset<Row> enrich(JavaRDD<GameItem> rdd) {
        Dataset<Row> df = sqlContext.createDataFrame(rdd, FootballGameItem.class);

        return df;
    }
}
