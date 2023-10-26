package App;

import App.Common.Fork;
import App.Common.Philosopher;

import java.util.HashMap;
import java.util.Map;

public class AppStart {
    private static final Map<Integer, String> philsName = new HashMap<Integer, String>() {{
        put(0, "Платон");
        put(1, "Аристотель");
        put(2, "Сократ");
        put(3, "Эпикур");
        put(4, "Зенон");
    }};
    public static void main(String[] args) {
        Philosopher[] phils = new Philosopher[5];
        Fork[] forks = new Fork[5];
        for (int i = 0; i < 5; i++) forks[i] = new Fork(i+1);
        for (int i = 0; i < 5; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % 5];

            if (i == 0 ) { //первый сначала берет левую вилку потом правую )))
                phils[i] = new Philosopher(philsName.get(i),rightFork,leftFork);
            } else {  // остальные всегда берут сначала правую вилку потом левую
                phils[i] = new Philosopher(philsName.get(i),leftFork,rightFork);
            }
            Thread thread = new Thread(phils[i]);
            thread.start();
        }
    }
}
