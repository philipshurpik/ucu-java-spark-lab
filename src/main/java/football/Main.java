package football;

import football.config.Conf;
import football.config.Const;
import football.services.GameService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", Const.DEV);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Conf.class);

        GameService gameService = context.getBean(GameService.class);
        gameService.doWork();
    }
}
