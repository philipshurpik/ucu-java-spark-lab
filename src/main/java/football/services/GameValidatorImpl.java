package football.services;

import football.models.GameItem;
import football.validators.GameFieldValidator;
import org.apache.spark.api.java.JavaRDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class GameValidatorImpl implements GameValidator, Serializable {
    @Autowired
    private List<GameFieldValidator> validators;

    @Override
    public JavaRDD<GameItem> validate(JavaRDD<GameItem> rdd) {
        for (GameFieldValidator validator : validators) {
            rdd = rdd.filter(validator::validate);
        }
        return rdd;
    }
}
