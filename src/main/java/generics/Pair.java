package generics;
/*
 * Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
 * Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из составляющих пары,
 * а также переопределение метода toString(), возвращающее строковое представление пары.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", first, second);
    }

    public static void main(String[] args) {
        Pair<Integer, ArrayList<String>> pair = new Pair<>(12, new ArrayList<>(Arrays.asList("Highway 66", "nonStop!")));
        System.out.println("getFirst() => " + pair.getFirst());
        System.out.println("getSecond() => " + pair.getSecond());
        System.out.println(pair);
    }
}
