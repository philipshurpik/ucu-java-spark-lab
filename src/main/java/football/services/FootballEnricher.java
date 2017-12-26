package football.services;

import football.enrichers.TeamEnricher;
import football.models.FootballGameItem;
import football.models.GameItem;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.spark.sql.functions.callUDF;
import static org.apache.spark.sql.functions.col;

@Service
public class FootballEnricher implements GameEnricher {
    @Autowired
    private SQLContext sqlContext;

    @Override
    public Dataset<Row> enrich(JavaRDD<GameItem> rdd) {
        Dataset<Row> dataFrame = sqlContext.createDataFrame(rdd, FootballGameItem.class);

        dataFrame=dataFrame.withColumn("team name",
                callUDF(TeamEnricher.class.getName(), col("from"), col("to")));

        return dataFrame;
    }
}
