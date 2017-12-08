package football.services;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FootballService implements GameService {

    @Autowired
    FootballDataSetLoader dataSetLoader;

    @Override
    public void doWork() {
        Dataset<Row> dataFrame = dataSetLoader.load();
        dataFrame.show();
        System.out.println("Football");
    }
}
