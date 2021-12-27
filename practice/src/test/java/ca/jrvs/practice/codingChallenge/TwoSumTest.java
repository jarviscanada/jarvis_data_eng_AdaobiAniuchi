package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Test;

public class TwoSumTest {
    TwoSum twoSum = new TwoSum();

    @Test
    public void shouldReturnArrayContaining0And1() {
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] expected = {0,1};
        int[] actual = twoSum.twoSumBruteForce(nums, target);

        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnArrayContaining0And12ndApproach() {
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] expected = {0,1};
        int[] actual = twoSum.twoSum(nums, target);

        Assert.assertArrayEquals(expected, actual);
    }
}