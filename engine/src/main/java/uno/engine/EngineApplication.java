package uno.engine;

import uno.engine.Entities.Game;

import java.util.ArrayList;
import java.util.List;


//@SpringBootApplication
public class EngineApplication {

	public static void main(String[] args) {
		//SpringApplication.run(EngineApplication.class, args);
        List<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(2);
        test.add(3);
		new Game(test);

	}

}
