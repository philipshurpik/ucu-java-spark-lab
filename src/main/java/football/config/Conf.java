package football.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("football")
@PropertySource("classpath:football_columns.properties")
public class Conf {

    @Autowired
    private SparkConf sparkConf;

    @Bean
    public SQLContext sqlContext(){
        return new SQLContext(sc());
    }

    @Bean
    public JavaSparkContext sc() {
       return new JavaSparkContext(sparkConf);
    }
}
