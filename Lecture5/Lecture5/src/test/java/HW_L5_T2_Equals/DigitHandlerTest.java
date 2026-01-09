package HW_L5_T2_Equals;

import org.example.HW_L5_T2_Equals.DigitHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigitHandlerTest {

    @Test
    void equalsSameObjectTest() {
        DigitHandler handler = new DigitHandler(5);
        assertTrue(handler.equals(handler));
    }

    @Test
    void equalsIdenticalValueTest() {
        DigitHandler handler1 = new DigitHandler(10);
        DigitHandler handler2 = new DigitHandler(10);
        assertTrue(handler1.equals(handler2));
        assertTrue(handler2.equals(handler1));
    }

    @Test
    void equalsDifferentValueTest() {
        DigitHandler handler1 = new DigitHandler(5);
        DigitHandler handler2 = new DigitHandler(7);
        assertFalse(handler1.equals(handler2));
        assertFalse(handler2.equals(handler1));
    }

    @Test
    void equalsNullTest() {
        DigitHandler handler = new DigitHandler(5);
        assertFalse(handler.equals(null));
    }

    @Test
    void equalsDifferentTypeTest() {
        DigitHandler handler = new DigitHandler(5);
        assertFalse(handler.equals("Not a DigitHandler"));
    }

    @Test
    void hashCodeCalculationTest() {
        DigitHandler handler = new DigitHandler(10);
        assertEquals(29, handler.hashCode());
    }

    @Test
    void hashCodeTest() {
        DigitHandler handler1 = new DigitHandler(10);
        DigitHandler handler2 = new DigitHandler(10);
        assertEquals(handler1.hashCode(), handler2.hashCode());
    }

    @Test
    void toStringTest() {
        DigitHandler handler = new DigitHandler(42);
        String expected = "DigitHandler{value=42}";
        assertEquals(expected, handler.toString());
    }
}
