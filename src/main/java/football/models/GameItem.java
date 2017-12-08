package football.models;

import org.springframework.context.annotation.Scope;

@Scope(value = "prototype")
public interface GameItem {
    public void initialize(String[] data);
}
