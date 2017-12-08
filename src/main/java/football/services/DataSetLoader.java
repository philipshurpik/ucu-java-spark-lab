package football.services;

import football.models.FootballGameItem;
import football.models.GameItem;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSetLoader {
    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private SQLContext sqlContext;

    @Autowired
    private BeanFactory beanFactory;

    public Dataset<Row> load() {
        JavaRDD<String> rdd = sc.textFile("data/rawData.txt");
        JavaRDD<GameItem> rowJavaRDD = rdd.map(line -> {
            String[] data = line.split(";");
            GameItem gameItem = new FootballGameItem();  // beanFactory.getBean(GameItem.class);
            gameItem.initialize(data);
            return gameItem;
        });
        return sqlContext.createDataFrame(rowJavaRDD, GameItem.class);
    }
}
