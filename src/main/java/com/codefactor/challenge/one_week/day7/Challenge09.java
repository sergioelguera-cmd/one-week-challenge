package com.codefactor.challenge.one_week.day7;

import java.util.*;

/**
 * Challenge 09 - Implement Custom Iterator
 *
 * Implement a custom Iterator that flattens a list of lists.
 * Also demonstrate Iterable pattern and enhanced for-loop support.
 *
 * Common interview question: Implement iterator for a 2D collection.
 */
public class Challenge09 {

    /** Flatten iterator over List<List<T>> */
    static class FlattenIterator<T> implements Iterator<T> {
        private final Iterator<List<T>> outerIter;
        private Iterator<T> innerIter;

        public FlattenIterator(List<List<T>> lists) {
            this.outerIter = lists.iterator();
            this.innerIter = Collections.emptyIterator();
            advance();
        }

        private void advance() {
            while (!innerIter.hasNext() && outerIter.hasNext()) {
                innerIter = outerIter.next().iterator();
            }
        }

        @Override
        public boolean hasNext() {
            return innerIter.hasNext();
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T value = innerIter.next();
            advance();
            return value;
        }
    }

    /** Range class implementing Iterable */
    static class Range implements Iterable<Integer> {
        private final int start, end;

        Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<>() {
                int current = start;

                @Override
                public boolean hasNext() { return current < end; }

                @Override
                public Integer next() {
                    if (!hasNext()) throw new NoSuchElementException();
                    return current++;
                }
            };
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(),
            Arrays.asList(4, 5),
            Arrays.asList(6)
        );

        System.out.println("Flatten iterator:");
        FlattenIterator<Integer> it = new FlattenIterator<>(lists);
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        System.out.println("\nCustom Range(1, 5):");
        for (int i : new Range(1, 5)) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
