package ru.sbrf.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayImplTests {
    private CustomArray customArray;

    @BeforeEach
    void setUp() {
        customArray = new CustomArrayImpl();
    }

    @Test
    void sizeEmptyArrayTest() {
        assertEquals(0, customArray.size());
    }

    @Test
    void isEmptyTest() {
        assertTrue(customArray.isEmpty());
    }

    @Test
    void addAndGetTest() {
        assertTrue(customArray.add("firstItem"));
        assertEquals(1, customArray.size());
        assertFalse(customArray.isEmpty());
        assertEquals("firstItem", customArray.get(0));

        customArray.add("secondItem");
        customArray.add("thirdItem");
        assertEquals("secondItem", customArray.get(1));
        assertEquals("thirdItem", customArray.get(2));
    }

    @Test
    void getTest_IndexOutOfBounds_Negative() {
        assertThrows(IndexOutOfBoundsException.class, () -> customArray.get(-1));
    }

    @Test
    void getTest_IndexOutOfBounds_TooLarge() {
        customArray.add("testItem");
        assertThrows(IndexOutOfBoundsException.class, () -> customArray.get(1));
    }

    @Test
    void setTest() {
        customArray.add("firstItem");
        Object old = customArray.set(0, "newItem");
        assertEquals("firstItem", old);
        assertEquals("newItem", customArray.get(0));
    }

    @Test
    void removeTest_ByIndex() {
        customArray.add("firstItem");
        customArray.add("secondItem");
        customArray.add("thirdItem");

        customArray.remove(1); // remove "B"
        assertEquals(2, customArray.size());
        assertEquals("firstItem", customArray.get(0));
        assertEquals("thirdItem", customArray.get(1));
    }

    @Test
    void removeTest_ByObject() {
        customArray.add("firstItem");
        customArray.add("secondItem");
        customArray.add("thirdItem");

        customArray.remove("secondItem"); // remove "B"
        assertEquals(2, customArray.size());
        assertEquals("firstItem", customArray.get(0));
        assertEquals("thirdItem", customArray.get(1));
    }

    @Test
    void containsTest() {
        customArray.add("firstItem");
        customArray.add("secondItem");

        assertTrue(customArray.contains("firstItem"));
        assertTrue(customArray.contains("secondItem"));
        assertFalse(customArray.contains("thirdItem"));
    }

    @Test
    void indexOfTest() {
        customArray.add("firstItem");
        customArray.add("secondItem");
        customArray.add("firstItem");

        assertEquals(0, customArray.indexOf("firstItem"));
        assertEquals(1, customArray.indexOf("secondItem"));
        assertEquals(-1, customArray.indexOf("thirdItem"));
    }

    @Test
    void AddAllTest() {
        Object[] items = {"firstItem", "secondItem", "thirdItem"};
        assertTrue(customArray.addAll(items));
        assertEquals(3, customArray.size());
        assertEquals("firstItem", customArray.get(0));
        assertEquals("secondItem", customArray.get(1));
        assertEquals("thirdItem", customArray.get(2));
    }

    @Test
    void ensureCapacityTest() {
        customArray.ensureCapacity(10);
        int capacity = customArray.getCapacity();
        assertTrue(capacity >= 10);
    }

    @Test
    void getCapacityTest() {
        assertEquals(1, customArray.getCapacity()); // default capacity
        for (int i = 0; i < 15; i++) {
            customArray.add("testItem");
        }
        assertTrue(customArray.getCapacity() >= 15);
    }

    @Test
    void reverseTest() {
        customArray.add("firstItem");
        customArray.add("secondItem");
        customArray.add("thirdItem");
        customArray.reverse();

        assertEquals("thirdItem", customArray.get(0));
        assertEquals("secondItem", customArray.get(1));
        assertEquals("firstItem", customArray.get(2));
    }

    @Test
    void toArrayTest() {
        customArray.add("firstItem");
        customArray.add("secondItem");
        Object[] arrayNew = customArray.toArray();

        assertEquals(2, arrayNew.length);
        assertEquals("firstItem", arrayNew[0]);
        assertEquals("secondItem", arrayNew[1]);

        // Проверка, что это копия
        arrayNew[0] = "changedItem";
        assertEquals("firstItem", customArray.get(0)); // оригинальный массив не изменится
    }
}
