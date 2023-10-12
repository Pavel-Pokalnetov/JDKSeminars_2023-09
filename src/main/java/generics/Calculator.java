package generics;
/*
 * Написать класс Калькулятор (необобщенный),
 * который содержит обобщенные статические методы: sum(), multiply(), divide(), subtract().
 * Параметры этих методов – два числа разного типа (но необязательно разного между собой),
 * над которыми должна быть произведена операция.
 */

public class Calculator {
    // Метод для сложения двух чисел
    public static <T extends Number> double sum(T number1, T number2) {
        return number1.doubleValue() + number2.doubleValue();
    }

    // Метод для умножения двух чисел
    public static <T extends Number> double multiply(T number1, T number2) {
        return number1.doubleValue() * number2.doubleValue();
    }

    // Метод для деления двух чисел
    public static <T extends Number> double divide(T number1, T number2) {
        if (number2.doubleValue() == 0) {
            throw new ArithmeticException("Деление на ноль невозможно");
        }
        return number1.doubleValue() / number2.doubleValue();
    }

    // Метод для вычитания двух чисел
    public static <T extends Number> double subtract(T number1, T number2) {
        return number1.doubleValue() - number2.doubleValue();
    }

    public static void main(String[] args) {

        System.out.println("-------------------------------------------------");
        System.out.println("Разность 5 и 3: " + Calculator.subtract(5, 3));
        System.out.println("Разность 5.0 и 3: " + Calculator.subtract(5.0, 3));
        System.out.println("Разность 5 и 3.0: " + Calculator.subtract(5, 3.0));
        System.out.println("Разность 5.0 и 3.0: " + Calculator.subtract(5.0, 3.0));
        System.out.println("-------------------------------------------------");
        System.out.println("Сумма 5 и 3: " + Calculator.sum(5, 3));
        System.out.println("Сумма 5.0 и 3: " + Calculator.sum(5.0, 3));
        System.out.println("Сумма 5 и 3.0: " + Calculator.sum(5, 3.0));
        System.out.println("Сумма 5.0 и 3.0: " + Calculator.sum(5.0, 3.0));
        System.out.println("-------------------------------------------------");
        System.out.println("Умножение 5 и 3: " + Calculator.multiply(5, 3));
        System.out.println("Умножение 5.0 и 3: " + Calculator.multiply(5.0, 3));
        System.out.println("Умножение 5 и 3.0: " + Calculator.multiply(5, 3.0));
        System.out.println("Умножение 5.0 и 3.0: " + Calculator.multiply(5.0, 3.0));
        System.out.println("-------------------------------------------------");
        System.out.println("Результат деления 5 на 3: " + Calculator.divide(5, 3));
        System.out.println("Результат деления 5.0 на 3: " + Calculator.divide(5.0, 3));
        System.out.println("Результат деления 5 на 3.0: " + Calculator.divide(5, 3.0));
        System.out.println("Результат деления 5.0 на 3.0: " + Calculator.divide(5.0, 3.0));
        System.out.println("-------------------------------------------------");
    }
}