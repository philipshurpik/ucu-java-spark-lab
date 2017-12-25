package football.services;

import football.models.GameItem;

public interface GameItemValidator {
    Boolean validate(GameItem gameItem);
}
