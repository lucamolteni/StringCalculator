package it.sevenpixel;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


public class StringCalculatorTest {
    @Test
    public void emptyStringShouldBeAddedToZero() throws Exception {
        checkCalculatorSum("", 0);
    }

    private void checkCalculatorSum(String numbers, int expectedVal) {
        assertEquals(expectedVal,new StringCalculator().add(numbers));
    }

    @Test
    public void aSingleNumberShouldAddedToItsValue() throws Exception {
        checkCalculatorSum("2", 2);
    }

    @Test
    public void aSingleNumberShouldAddedToItsValue3() throws Exception {
        checkCalculatorSum("3", 3);
    }

    @Test
    public void twoNumberSeparatedByCommaShouldBeAddedToTheirSum() throws Exception {
        checkCalculatorSum("2,3", 5);
    }

    @Test
    public void testWithTwoNumbers2() throws Exception {
        checkCalculatorSum("22,3",25);
    }

    @Test
    public void testWithThreeNumbers() throws Exception {
        checkCalculatorSum("2,3,5",10);
    }

    @Test
    public void testWithFourNumbers() throws Exception {
        checkCalculatorSum("2,3,5,10",20);
    }

    @Test
    public void newlineIsAValidSeparator() throws Exception {
        checkCalculatorSum("2,3,5\n10",20);
    }

    @Test
    public void shouldAcceptArbitrarySeparator() throws Exception {
        checkCalculatorSum("\\;\n2;3;5",10);
    }


    @Test(expected = NegativeNumberException.class)
    public void shouldThrowsExceptionWithOneNegativeNumber() throws Exception {
        checkCalculatorSum("-1", 0);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowsExceptionWithTwoNegativeNumbers() throws Exception {
        thrown.expect(NegativeNumberException.class);
        thrown.expectMessage("No negative numbers allowed: [-1, -2]");

        checkCalculatorSum("-1,-2", 0);
    }

    @Test
    public void shouldIgnoreBiggerThanOneThousand() {
        checkCalculatorSum("2,1001", 2);
    }

    @Test
    public void shouldAcceptArbitraryLengthSeparator() throws Exception {
        checkCalculatorSum("\\;;;\n2;;;3;;;5;;;7",17);
    }
}
