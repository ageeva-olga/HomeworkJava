package org.example.HW_L5_T1_Comparators;

import java.util.Comparator;

public class CustomDigitComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 == null || o2 == null) {
            throw new IllegalArgumentException("Numbers cannot be null");
        }
        boolean o1Even = o1 % 2 == 0;
        boolean o2Even = o2 % 2 == 0;

        //Если оба числа оба четные или оба нечетные — сортируем по возрастанию
        if (o1Even == o2Even) {
            return Integer.compare(o1, o2);
        }

        //Четные идут перед нечетными
        if (o1Even) {
            return -1; // o1 — четное, значит, должно быть раньше
        }
        else {
            return 1;  // o1 — нечетное, значит, должно быть позже
        }
    }
}
