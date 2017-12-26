package football.config;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static football.config.Const.DEV;

@Profile(DEV)
@Configuration
public class DevConfig {
    @Bean
    public SparkConf sparkConf(){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("football-dev");
        sparkConf.setMaster("local[*]");
        sparkConf.set("dataPath", "data/footballData.txt");
        return sparkConf;
    }
}
