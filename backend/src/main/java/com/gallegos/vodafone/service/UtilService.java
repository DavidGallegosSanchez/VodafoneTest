package com.gallegos.vodafone.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UtilService {

    private static final Logger logger = LogManager.getLogger(UtilService.class);

    static final String WRONG_SORT_VALUE = "Sort value should be low_high or high_low";
    static final String NO_MISSING_NUMBER = "There is no missing number in the list.";

    private static final Random RANDOM = new Random();

    public int findMissingNumber(List<Integer> list, int n) {

        for (int i = 1; i <= n; i++) {
            if (!list.contains(i)) {
                return i;
            }
        }

        // If no integer is missing, return MissingNumberException
        logger.warn(NO_MISSING_NUMBER);
        throw new MissingNumberException(NO_MISSING_NUMBER);

    }

    public List<Integer> sortList(List<Integer> list) {
        return list.stream().sorted().toList();
    }

    public List<Integer> sortListReverseOrder(List<Integer> list) {
        return list.stream().sorted(Comparator.reverseOrder()).toList();
    }

    public List<Integer> generateRandonList(Integer maxValue) {

        return RANDOM.ints(1, maxValue + 1)
                .distinct()
                .limit(maxValue - 1L)
                .boxed()
                .toList();
    }

    public List<Integer> generateFibonacciSequence(int value) {
        List<Integer> fibonacciSequence = new ArrayList<>();
        int a = 0;
        int b = 1;

        while (a <= value) {
            fibonacciSequence.add(a);
            int temp = a;
            a = b;
            b = temp + b;
        }

        return fibonacciSequence;
    }

    public List<Integer> randomizeWithCenter(List<Integer> sequence, int center) {
        List<Integer> randomizedSequence = new ArrayList<>(sequence);
        Collections.shuffle(randomizedSequence);

        int centerIndex = randomizedSequence.indexOf(center);

        // Move the center element to the middle if it's not already there
        if (centerIndex != randomizedSequence.size() / 2) {
            Collections.swap(randomizedSequence, centerIndex, randomizedSequence.size() / 2);
        }

        return randomizedSequence;
    }
}
