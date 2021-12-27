package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class OddEvenTest {
    OddEven oddEven = new OddEven();
    private final int testEvenInteger = 40000;
    private final int testOddInteger = 1567;

    @Test
    public void shouldReturnEvenWhenUsingModSolution() {
        Assert.assertEquals("even", oddEven.oddEvenWithMod(testEvenInteger));
    }

    @Test
    public void shouldReturnOddWhenUsingModSolution() {
        Assert.assertEquals("odd", oddEven.oddEvenWithMod(testOddInteger));
    }

    @Test
    public void shouldReturnEvenWhenUsingBitOperatorSolution() {
        Assert.assertEquals("even", oddEven.oddEvenWithBit(testEvenInteger));
    }

    @Test
    public void shouldReturnOddWhenUsingBitOperatorSolution() {
        Assert.assertEquals("odd", oddEven.oddEvenWithBit(testOddInteger));
    }
}