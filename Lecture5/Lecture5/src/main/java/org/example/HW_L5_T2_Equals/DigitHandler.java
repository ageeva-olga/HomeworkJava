package org.example.HW_L5_T2_Equals;

import java.util.Objects;

public class DigitHandler {
    private int value;

    public DigitHandler(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DigitHandler that = (DigitHandler) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        int total = 19;
        return value + total;
    }

    @Override
    public String toString() {
        return "DigitHandler{" +
                "value=" + value +
                '}';
    }

    // Геттер при необходимости
    public int getValue() {
        return value;
    }
}
