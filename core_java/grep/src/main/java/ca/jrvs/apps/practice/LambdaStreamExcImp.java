package ca.jrvs.apps.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * LambdaStreamExcImp Implementation for LambdaStreamExc to demonstrate Lambda and Stream APIs.
 */
public class LambdaStreamExcImp implements LambdaStreamExc {
    @Override
    public Stream<String> createStrStream(String[] strings) {
        return Arrays.stream(strings);
    }

    @Override
    public Stream<String> toUpperCase(String[] strings) {
        Stream<String> stringArrayStream = createStrStream(strings);
        return stringArrayStream.map(String::toUpperCase);
    }

    @Override
    public Stream<String> filter(Stream<String> stringStream, String pattern) {
        return stringStream.filter(string -> !string.matches(pattern));
    }

    @Override
    public IntStream createIntStream(int[] arr) {
        return IntStream.of(arr);
    }

    @Override
    public IntStream createIntStream(int start, int end) {
        return IntStream.rangeClosed(start, end);
    }

    @Override
    public <E> List<E> toList(Stream<E> stream) {
        return stream.collect(Collectors.toList());
    }

    @Override
    public List<Integer> toList(IntStream intStream) {
        List<Integer> integerList = new ArrayList<>();
        intStream.forEach(integerList::add);
        return integerList;
    }

    @Override
    public DoubleStream squareRootIntStream(IntStream intStream) {
        return intStream.mapToDouble(Math::sqrt);
    }

    @Override
    public IntStream getOdd(IntStream intStream) {
        return intStream.filter(i -> i % 2 != 0);
    }

    @Override
    public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
        return string -> System.out.println(prefix +string+ suffix);
    }

    @Override
    public void printMessages(String[] messages, Consumer<String> printer) {
        Stream<String> messagesStream = createStrStream(messages);
        messagesStream.forEach(printer);
    }

    @Override
    public void printOdd(IntStream intStream, Consumer<String> printer) {
        IntStream oddIntsStream = getOdd(intStream);
        Stream<String> oddIntsStreamToString = oddIntsStream.mapToObj(i -> Integer.toString(i));
        oddIntsStreamToString.forEach(printer);
    }

    @Override
    public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
        return ints.flatMap(Collection::stream).map(i -> i*i);
    }
}
