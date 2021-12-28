package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-af1b6c8307a94dc08df89309b190564f
 */
public class FibonacciNumber {
    /**
     * Get the nth fibonacci number using recursion
     * O(n): 2 ^ n
     * Justification: The leaves or nodes recursive tree os height n increases exponentially as the size of n increases
     * @param n The nth term to find the equivalent fibonacci number
     * @return The fibonacci number
     */
    public int fibonacciNumberRecursion(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            return fibonacciNumberRecursion(n - 1) + fibonacciNumberRecursion(n - 2);
        }
    }

    /**
     * Get the nth fibonacci number using bottom-up approach to dynamic programming
     * Big-O: O(n)
     * Justification: You have to compute every fibonacci number until the nth fibonacci number. Thus, I used a for loop that counts from 2 - n.
     * @param n The nth term to find the equivalent fibonacci number
     * @return The fibonacci number
     */
    public int fibonacciNumberDPSolution(int n) {
        if(n == 0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        int[] fibonacciNumbers = new int[n + 1];
        fibonacciNumbers[0] = 0;
        fibonacciNumbers[1] = 1;
        fibonacciNumbers[2] = 1;

        for (int i = 3; i < fibonacciNumbers.length; i++) {
            fibonacciNumbers[i] = fibonacciNumbers[i-1] + fibonacciNumbers[i-2];
        }

        return fibonacciNumbers[n];
    }

    /**
     * Get the nth fibonacci number using another approach to dynamic programming
     * Big-O: O(n)
     * Justification: The loop only runs once from the counter specified 2 to n.
     * @param n The nth term to find the equivalent fibonacci number
     * @return The fibonacci number
     */
    public int fibonacciNumberOtherDPSolution(int n) {
        int[] startingPoint = {0, 1};
        int counter = 2;

        for (int i = counter; i <= n; i++) {
            int nextFn = startingPoint[0] + startingPoint[1];
            startingPoint[0] = startingPoint[1];
            startingPoint[1] = nextFn;
        }

        return n <= 1 ? startingPoint[n] : startingPoint[1];
    }
}
