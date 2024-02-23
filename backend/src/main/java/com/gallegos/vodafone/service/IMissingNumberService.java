package com.gallegos.vodafone.service;

import com.gallegos.vodafone.model.Calculate;
import com.gallegos.vodafone.model.ElementsList;
import com.gallegos.vodafone.model.Order;

import java.util.List;

public interface IMissingNumberService {
	
	/**
	* Calculate the missing number in a list
	* @param elementsList Unordered list of elements
	* @return Output object with missing number
	*/	
	public Order calculate(ElementsList elementsList);

	public Calculate calculateMap(ElementsList elementsList);
	
	/**
	* Calculate the missing number in an unordered list and return both the missing
	* number and the sorted list
	* @param elementsList Unordered list of elements
	* @param sortType Sorting filter
	* @return Output object with missing number and list sorted according to filter
	*/
	public Order calculateSort(List<Integer> elementsList, String sortType) throws MissingNumberException;
	
	/**
	* Calculates the result of the missing value in a random list that it will return.
	* @param maxValue Filter to generate a list with that size of values
	* @return Output object with missing number and list according to the value size
	*/
	public Order calculateRandom(Integer maxValue);
	
	/**
	* Calculate the fibonacci sequence up to the given number and return that sequence with random
	* order but with the given number in the center.
	* @param value Maximum value of the Fibonacci sequence
	* @return Output object with the Fibonacci sequence and the given number in the center
	*/
	public Order calculateFibonacci(Integer value);
	
	/**
	* List the last N calculations made
	* @param lastCalculations Value indicating the number of the last orders made
	* @return Output object with the list of the specified last orders made
	*/
	public List<Order> missingNumbers(Integer lastCalculations) throws MissingNumberException;

}
