package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/Two-Sum-da40b30768644768b29776f06648894a
 */
public class TwoSum {
    /**
     * Return an array containing the two indices of elements of the given array that sum to the given target using the Brute force approach
     * Big-O: O(n^2)
     * Justification: The nested for-loops makes it such that for each item in the nums array, we perform another iteration through the nums array.
     * @param nums The array of numbers
     * @param target The target sum
     * @return Array containing the two indices i,j where nums[i] + nums[j] = target
     */
    public int[] twoSumBruteForce(int[] nums, int target) {
        int[] output = {0, 0};
        for (int i = 0; i < nums.length - 1; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    output[0] = i;
                    output[1] = j;
                }
            }
        }

        return output;
    }

    /**
     * Return an array containing the two indices of elements of the given array that sum to the given target using the Brute force approach
     * Big-O: O(n)
     * Justification: This approach only traverses the entire nums array once in the worst case scenario where it has to go through the entire array before it finds a difference.
     * @param nums The array of numbers
     * @param target The target sum
     * @return Array containing the two indices i,j where nums[i] + nums[j] = target
     */
    public int[] twoSum(int[] nums, int target) {
        int[] output = {0, 0};
        Map<Integer, Integer> differences = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int difference = target - nums[i];
            if(differences.containsKey(difference)) {
                output[0] = differences.get(difference);
                output[1] = i;
            } else {
                differences.put(nums[i], i);
            }
        }

        return output;
    }
}
