package generics;
/*
 * Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true,
 * если они одинаковые, и false в противном случае. Массивы могут быть любого типа данных,
 * но должны иметь одинаковую длину и содержать элементы одного типа.
 * */

import java.util.Arrays;

public class ArrayComparator {
    public static <T> boolean compareArreys(T[] array1, T[] array2) {
        boolean result = false;
        //проверим что оба массива не null
        if (array1 != null && array2 != null) {
            //если оба массива - это один и тот же объект
            if (array1 == array2) {
                result = true;
            } else if (array1.length == array2.length) {
                //если типы данных в массивах одинаковые, проверяем через Arrays.deepEquals
                if (array1.getClass().getComponentType().equals(array2.getClass().getComponentType())) {
                    result = Arrays.deepEquals(array1, array2);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Одинаковые массивы с одинаковым содержимым");
        Integer[] intArray1 = new Integer[]{1, 2, 3};
        Integer[] intArray2 = new Integer[]{1, 2, 3};
        System.out.println(Arrays.toString(intArray1));
        System.out.println(Arrays.toString(intArray2));
        System.out.println(compareArreys(intArray1, intArray2));
        System.out.println();

        String[] strArray1 = {"a", "b", "c"};
        String[] strArray2 = {"a", "b", "c"};
        System.out.println(Arrays.toString(strArray1));
        System.out.println(Arrays.toString(strArray2));
        System.out.println(compareArreys(strArray1, strArray2));
        System.out.println();

        Double[] doubleArray1 = new Double[]{1.1, 2.2, 3.3};
        Double[] doubleArray2 = new Double[]{1.1, 2.2, 3.3};
        System.out.println(Arrays.toString(doubleArray1));
        System.out.println(Arrays.toString(doubleArray2));
        System.out.println(compareArreys(doubleArray1, doubleArray2));
        System.out.println();

        System.out.println("Пример с различной длиной массивов");
        Integer[] shortArray = new Integer[]{1, 2};
        System.out.println(Arrays.toString(shortArray));
        System.out.println(Arrays.toString(intArray1));
        System.out.println(compareArreys(shortArray, intArray1));
        System.out.println();


        System.out.println("Пример с массивами разных типов");
        Integer[] mixedArray1 = {1, 2, 3};
        String[] mixedArray2 = {"a", "b", "c"};
        System.out.println(Arrays.toString(mixedArray1));
        System.out.println(Arrays.toString(mixedArray2));
        System.out.println(compareArreys(mixedArray1, mixedArray2));
        System.out.println();

        System.out.println("Массивы одинаковой длины с одинаковыми типами, но разным содержимым");
        strArray1 = new String[]{"..", "b", "1"};
        strArray2 = new String[]{"к", "b11", "c"};
        System.out.println(Arrays.toString(strArray1));
        System.out.println(Arrays.toString(strArray2));
        System.out.println(compareArreys(strArray1, strArray2));
        System.out.println();

        System.out.println("Массивы указывающие на один объект");
        strArray1 = new String[]{"..", "b", "1"};
        strArray2 = strArray1;
        System.out.println(Arrays.toString(strArray1)+" => " +strArray1);
        System.out.println(Arrays.toString(strArray2)+" => " +strArray2);
        System.out.println(compareArreys(strArray1, strArray2));
        System.out.println();

        System.out.println("Массивы с null");
        strArray1 = null;
        strArray2 = new String[]{"a", "b", "c"};
        System.out.println(Arrays.toString(strArray1));
        System.out.println(Arrays.toString(strArray2));
        System.out.println(compareArreys(strArray1, strArray2));
        System.out.println();
    }

}
