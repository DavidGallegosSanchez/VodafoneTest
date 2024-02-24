package com.gallegos.vodafone.service;

import com.gallegos.vodafone.model.*;

import java.util.List;

public interface IFrontMissingNumberService {

    /**
     * Calculate the missing number in a list
     * @param elementsList Unordered list of elements
     * @return Output object with missing number
     */
    Calculate calculate(ElementsList elementsList);

    /**
     * Calculate the missing number in an unordered list and return both the missing
     * number and the sorted list
     * @param elementsList Unordered list of elements
     * @param sortType Sorting filter
     * @return Output object with missing number and list sorted according to filter
     */
    CalculateSort calculateSort(List<Integer> elementsList, String sortType) throws MissingNumberException;

    /**
     * Calculates the result of the missing value in a random list that it will return.
     * @param maxValue Filter to generate a list with that size of values
     * @return Output object with missing number and list according to the value size
     */
    CalculateRandom calculateRandom(Integer maxValue);

    /**
     * Calculate the fibonacci sequence up to the given number and return that sequence with random
     * order but with the given number in the center.
     * @param value Maximum value of the Fibonacci sequence
     * @return Output object with the Fibonacci sequence and the given number in the center
     */
    CalculateFibonacci calculateFibonacci(Integer value);
}
