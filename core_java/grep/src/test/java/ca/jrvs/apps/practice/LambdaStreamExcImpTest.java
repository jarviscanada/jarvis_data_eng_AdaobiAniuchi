package ca.jrvs.apps.practice;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class LambdaStreamExcImpTest {
    private final String[] testStringArray = {"one", "two", "three", "four", "five"};
    private final int[] testIntArray = {1, 2, 3};
    private final LambdaStreamExcImp cut = new LambdaStreamExcImp();
    private final ByteArrayOutputStream stdOutContent = new ByteArrayOutputStream();
    private final PrintStream originalStdOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(stdOutContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalStdOut);
    }

    @Test
    @DisplayName("Should return a stream of strings")
    void createStrStream() {
        Stream<String> actual = cut.createStrStream(testStringArray);
        Stream<String> expected = Stream.of("one", "two", "three", "four", "five");
        Iterator<String> actualIter = actual.iterator();
        Iterator<String> expectedIter = expected.iterator();
        while (actualIter.hasNext() && expectedIter.hasNext()) {
            Assertions.assertEquals(expectedIter.next(), actualIter.next());
        }
        Assertions.assertTrue(!expectedIter.hasNext() && !actualIter.hasNext());
    }

    @Test
    @DisplayName("Should return a stream of strings all in uppercase")
    void toUpperCase() {
        Stream<String> actual = cut.toUpperCase(testStringArray);
        Stream<String> expected = Stream.of("ONE", "TWO", "THREE", "FOUR", "FIVE");

        Iterator<String> actualIter = actual.iterator();
        Iterator<String> expectedIter = expected.iterator();
        while (actualIter.hasNext() && expectedIter.hasNext()) {
            Assertions.assertEquals(expectedIter.next(), actualIter.next());
        }
        Assertions.assertTrue(!expectedIter.hasNext() && !actualIter.hasNext());
    }

    @Test
    @DisplayName("Should return a stream of strings filtered by the pattern " +
            "with none of the elements in the new stream containing the pattern")
    void filter() {
        Stream<String> actual = cut.filter(Arrays.stream(testStringArray), ".*e.*");
        Stream<String> expected = Stream.of("two", "four");

        Iterator<String> actualIter = actual.iterator();
        Iterator<String> expectedIter = expected.iterator();
        while (actualIter.hasNext() && expectedIter.hasNext()) {
            Assertions.assertEquals(expectedIter.next(), actualIter.next());
        }
        Assertions.assertTrue(!expectedIter.hasNext() && !actualIter.hasNext());
    }

    @Test
    @DisplayName("Should return a stream of integers")
    void createIntStream() {
        IntStream actual = cut.createIntStream(testIntArray);
        IntStream expected = IntStream.of(testIntArray);
        PrimitiveIterator.OfInt actualIter = actual.iterator();
        PrimitiveIterator.OfInt expectedIter = expected.iterator();
        while (actualIter.hasNext() && expectedIter.hasNext()) {
            Assertions.assertEquals(expectedIter.next(), actualIter.next());
        }
        Assertions.assertTrue(!expectedIter.hasNext() && !actualIter.hasNext());
    }

    @Test
    @DisplayName("Should convert a stream of a given type to a list of that type")
    void toList() {
        Stream<String> testStringStream = Stream.of("Apple", "Bag", "Cat");
        List<String> actual = cut.toList(testStringStream);

        Assertions.assertInstanceOf(List.class, actual, "It is not an instance of List");
    }

    @Test
    @DisplayName("Should return a list of integers from an IntStream")
    void intStreamToList() {
        IntStream testIntStream = IntStream.of(testIntArray);
        List<Integer> actual = cut.toList(testIntStream);
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);

        Assertions.assertInstanceOf(List.class, actual, "It is not an instance of List");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should create an IntStream with integers from a given start int to the end int")
    void createAnIntStreamWithIntegersWithinARange() {
        IntStream actual = cut.createIntStream(1, 3);
        IntStream expected = IntStream.of(1, 2, 3);

        PrimitiveIterator.OfInt actualIter = actual.iterator();
        PrimitiveIterator.OfInt expectedIter = expected.iterator();
        while (actualIter.hasNext() && expectedIter.hasNext()) {
            Assertions.assertEquals(expectedIter.next(), actualIter.next());
        }
        Assertions.assertTrue(!expectedIter.hasNext() && !actualIter.hasNext());
    }

    @Test
    @DisplayName("Should return an IntStream of square root of numbers in a given IntStream")
    void squareRootIntStream() {
        IntStream testIntStream = IntStream.of(4, 25, 100);
        DoubleStream actual = cut.squareRootIntStream(testIntStream);
        DoubleStream expected = DoubleStream.of(2, 5, 10);

        PrimitiveIterator.OfDouble actualIter = actual.iterator();
        PrimitiveIterator.OfDouble expectedIter = expected.iterator();
        while (actualIter.hasNext() && expectedIter.hasNext()) {
            Assertions.assertEquals(expectedIter.next(), actualIter.next());
        }
        Assertions.assertTrue(!expectedIter.hasNext() && !actualIter.hasNext());
    }

    @Test
    @DisplayName("Should filter out all even numbers and return a stream containing just odd numbers")
    void getOdd() {
        IntStream actual = cut.getOdd(IntStream.of(1, 2, 3));
        IntStream expected = IntStream.of(1, 3);

        PrimitiveIterator.OfInt actualIter = actual.iterator();
        PrimitiveIterator.OfInt expectedIter = expected.iterator();
        while (actualIter.hasNext() && expectedIter.hasNext()) {
            Assertions.assertEquals(expectedIter.next(), actualIter.next());
        }
        Assertions.assertTrue(!expectedIter.hasNext() && !actualIter.hasNext());
    }

    @Test
    @DisplayName("Should generate a lambda printer that prints a given message with a prefix and suffix")
     void getLambdaPrinter() {
        Consumer<String> lambdaPrinter = cut.getLambdaPrinter("start>", "<end");
        lambdaPrinter.accept("hello");
        Assertions.assertInstanceOf(Consumer.class, lambdaPrinter, "It is not a consumer.");
        Assertions.assertEquals("start>hello<end\n", stdOutContent.toString());
    }

    @Test
    @DisplayName("Should print the string of messages with the defined prefix and suffix using the lambda printer")
    void printMessages() {
        String[] messages = {"a", "b", "c"};
        Consumer<String> lambdaPrinter = cut.getLambdaPrinter("msg:", "!");
        cut.printMessages(messages, lambdaPrinter);

        Assertions.assertEquals("msg:a!\nmsg:b!\nmsg:c!\n", stdOutContent.toString());
    }

    @Test
    @DisplayName("Should print all the odd numbers in a stream after filtering out the even numbers")
    void printOdd() {
        IntStream intStream = cut.createIntStream(0, 5);
        Consumer<String> lambdaPrinter = cut.getLambdaPrinter("odd number:", "!");
        cut.printOdd(intStream, lambdaPrinter);

        Assertions.assertEquals("odd number:1!\nodd number:3!\nodd number:5!\n", stdOutContent.toString());
    }

    @Test
    @DisplayName("Should return the square of numbers in a stream of a list of integers as a stream of integers")
    void flatNestedInt() {
        List<Integer> listOfInts = new ArrayList<>();
        listOfInts.add(1);
        listOfInts.add(2);
        listOfInts.add(3);
        Stream<List<Integer>> testInts = Stream.of(listOfInts);
        Stream<Integer> expected = Stream.of(1, 4, 9);
        Stream<Integer> actual = cut.flatNestedInt(testInts);

        Iterator<Integer> actualIter = actual.iterator();
        Iterator<Integer> expectedIter = expected.iterator();
        while (actualIter.hasNext() && expectedIter.hasNext()) {
            Assertions.assertEquals(expectedIter.next(), actualIter.next());
        }
        Assertions.assertTrue(!expectedIter.hasNext() && !actualIter.hasNext());
    }
}