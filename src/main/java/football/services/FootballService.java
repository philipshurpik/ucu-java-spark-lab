package football.services;

import org.springframework.stereotype.Service;

@Service
public class FootballService implements GameService {

    @Override
    public void doWork() {
        System.out.println("Football");
    }
}
