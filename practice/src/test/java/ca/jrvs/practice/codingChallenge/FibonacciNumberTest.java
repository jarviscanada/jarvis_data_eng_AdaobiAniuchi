package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciNumberTest {
    FibonacciNumber fibonacciNumber = new FibonacciNumber();

    @Test
    public void shouldReturnNthFibonacciNumberUsingRecursion() {
        int expected = 5;
        int actual = fibonacciNumber.fibonacciNumberRecursion(5);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnNthFibonacciNumberUsingDPSolution() {
        int expected = 5;
        int actual = fibonacciNumber.fibonacciNumberDPSolution(5);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnNthFibonacciNumberUsingOtherDPSolution() {
        int expected = 34;
        int actual = fibonacciNumber.fibonacciNumberOtherDPSolution(9);
        assertEquals(expected, actual);
    }
}