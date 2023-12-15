package com.gallegos.vodafone.service;

import com.gallegos.vodafone.model.ElementsList;
import com.gallegos.vodafone.model.Order;

import java.util.List;

public interface IMissingNumberService {
	
	/**
	* Calculate the missing number in a list
	* @return Output object.
	*/	
	public Order calculate(ElementsList elementsList);
	
	/**
	* Calculate the missing number in an unordered list and return both the missing
	* number and the sorted list.
	* @return Output object
	*/
	public Order calculateSort(List<Integer> elementsLits, String sortType) throws MissingNumberException;
	
	/**
	* Calculates the result of the missing value in a random list that it will return.
	* @return Output object
	*/
	public Order calculateRandom(Integer maxValue);
	
	/**
	* Calculate the fibonacci sequence up to the given number and return that sequence with random
	* order but with the given number in the center.
	* @return Output object
	*/
	public Order calculateFibonacci(Integer value);
	
	/**
	* List the last N calculations made
	* @return Output object
	*/
	public List<Order> missingNumbers(Integer lastCalculations);

}
