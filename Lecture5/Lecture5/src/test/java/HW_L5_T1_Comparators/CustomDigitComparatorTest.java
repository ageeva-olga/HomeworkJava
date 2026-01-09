package HW_L5_T1_Comparators;

import org.example.HW_L5_T1_Comparators.CustomDigitComparator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomDigitComparatorTest {

    private final CustomDigitComparator comparator = new CustomDigitComparator();

    @Test
    void evenBeforeOddTest() {
        assertEquals(-1, comparator.compare(2, 3));
        assertEquals(1, comparator.compare(3, 2));
    }

    @Test
    void allEvenNumbersTest() {
        assertEquals(-1, comparator.compare(2, 4));
        assertEquals(1, comparator.compare(4, 2));
        assertEquals(0, comparator.compare(2, 2));
    }

    @Test
    void allOddNumbersTest() {
        assertEquals(-1, comparator.compare(1, 3));
        assertEquals(1, comparator.compare(3, 1));
        assertEquals(0, comparator.compare(3, 3));
    }

    @Test
    void compareNullThrowsExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> comparator.compare(null, 1));
        assertThrows(IllegalArgumentException.class, () -> comparator.compare(1, null));
        assertThrows(IllegalArgumentException.class, () -> comparator.compare(null, null));
    }

    @Test
    void reverseOrderNotEqualTest() {
        assertTrue(comparator.compare(2, 3) < 0); // even < odd
        assertTrue(comparator.compare(3, 2) > 0); // odd > even
    }
}