package football.services;

import football.models.GameItem;
import football.validators.GameFieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class GameItemValidatorImpl implements GameItemValidator, Serializable {
    @Autowired
    private List<GameFieldValidator> validators;


    @Override
    public Boolean validate(GameItem gameItem) {
        Boolean result = validators.stream()
                .map(validator -> validator.validate(gameItem))
                .allMatch(a -> a.equals(true));
        return result;
    }
}
