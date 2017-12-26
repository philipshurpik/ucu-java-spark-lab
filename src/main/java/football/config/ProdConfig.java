package football.config;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static football.config.Const.PROD;

@Profile(PROD)
@Configuration
public class ProdConfig {
    @Bean
    public SparkConf sparkConf(){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("football");
        // here set some production path for data, for example from s3
        sparkConf.set("dataPath", "s3://s3.eu-central-1.amazonaws.com/haircolorbot/ucu/footballData.txt");
        return sparkConf;
    }
}
