package football.services;

import football.models.*;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSetLoader {
    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private SQLContext sqlContext;

    public Dataset<Row> load() {
        JavaRDD<String> rdd = sc.textFile("data/footballData.txt");
        JavaRDD<GameItem> rowJavaRDD = rdd.map(line -> {
            String[] data = line.split(";");
            return new FootballGameItem(data);
        });
        return sqlContext.createDataFrame(rowJavaRDD, GameItem.class);
    }
}
