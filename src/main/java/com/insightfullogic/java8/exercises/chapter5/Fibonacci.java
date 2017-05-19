package com.insightfullogic.java8.exercises.chapter5;

import com.insightfullogic.java8.exercises.Exercises;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    private final Map<Integer, Long> cache = new HashMap();

    public Fibonacci() {
        cache.put(0, 0L);
        cache.put(1, 1L);
    }

    public long fibonacci(int x) {
        return cache.computeIfAbsent(x, n -> fibonacci(n - 1) + fibonacci( n - 2));
    }

}
