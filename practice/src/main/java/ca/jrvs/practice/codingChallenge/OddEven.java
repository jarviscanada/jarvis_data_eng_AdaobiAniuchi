package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-309947004ead48e8b27c76d65cd57bac
 */
public class OddEven {
    /**
     * Check if a number is odd or even using Modulo operation
     * Big-O: O(1)
     * Justification: it is an arithmetic operation
     * @param num The number to check
     * @return String indicating whether num is even or odd
     */
    public String oddEvenWithMod(int num) {
        return num % 2 == 0 ? "even" : "odd";
    }

    /**
     * Check if a number is odd or even using bit operator
     * @param num The number to check
     * @return String indicating whether it is even or odd
     */
    public String oddEvenWithBit(int num) {
        return (num & 1) == 0 ? "even" : "odd";
    }
}
