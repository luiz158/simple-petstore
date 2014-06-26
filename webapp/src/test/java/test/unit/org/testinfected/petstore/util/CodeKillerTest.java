package test.unit.org.testinfected.petstore.util;

import org.junit.Test;
import org.testinfected.petstore.util.CodeKiller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CodeKillerTest {

    @Test public void
    testCalculateEmptyString() {
        assertEquals(CodeKiller.calculate(""), 0);
    }

    @Test public void
    testCalculateOne() {
        assertEquals(CodeKiller.calculate("1"), 1);
    }

    @Test public void
    testCalculateSum() {
        assertEquals(CodeKiller.calculate("1,2,3"), 6);
    }

    @Test(expected=IllegalArgumentException.class) public void
    testCalculateNotANumber() {
        CodeKiller.calculate("abc");
    }

    @Test(expected=IllegalArgumentException.class) public void testCalculateSeb() {
        CodeKiller.calculate("" + ((long) Integer.MAX_VALUE + 1));
    }

    @Test(expected=IllegalArgumentException.class) public void
    testCalculateFloat() {
        CodeKiller.calculate("1.5");
    }

    @Test public void testCalculateNegative() {
        assertEquals(CodeKiller.calculate("-1,2,-5"), -4);
    }

    @Test public void testCalculateNegative2() {
        assertEquals(CodeKiller.calculate(Integer.MAX_VALUE + ",1"), Integer.MIN_VALUE);
    }
}
