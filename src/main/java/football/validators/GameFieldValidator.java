package football.validators;

import football.models.GameItem;

public interface GameFieldValidator {
    Boolean validate(GameItem gameItem);
}
