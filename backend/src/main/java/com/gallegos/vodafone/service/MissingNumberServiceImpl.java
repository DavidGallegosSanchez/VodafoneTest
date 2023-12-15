package com.gallegos.vodafone.service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.gallegos.vodafone.repository.IOrderRepository;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gallegos.vodafone.model.ElementsList;
import com.gallegos.vodafone.model.Order;

@Service
public class MissingNumberServiceImpl implements IMissingNumberService{

	private static final Logger logger = LogManager.getLogger(MissingNumberServiceImpl.class);

	static final String WRONG_SORT_VALUE = "Sort value should be low_high or high_low";
	static final String NO_MISSING_NUMBER = "There is no missing number in the list.";

	private static final Random RANDOM = new Random();

	@Autowired
	IOrderRepository orderRepository;

	@Override
	public Order calculate(ElementsList elementsList) {

		Order out = new Order();
		int listLength = elementsList.getList().size();

		List<Integer> listSorted = sortList(elementsList.getList());
		
		out.setMissingNumber(findMissingNumber(listSorted,listLength));
		out.setCreateAt(LocalDateTime.now());

		orderRepository.save(out);
		return out;
	}

	@Override
	public Order calculateSort(List<Integer> elementsLits, String sortType) throws MissingNumberException {
		Order out = new Order();
		int listLength = elementsLits.size();
		List<Integer> listSorted;

		if("low_high".equals(sortType)){
			listSorted = sortList(elementsLits);
		}else if("high_low".equals(sortType)){
			listSorted = sortListReverseOrder(elementsLits);
		} else {
			logger.error(WRONG_SORT_VALUE);
			throw new MissingNumberException(WRONG_SORT_VALUE);
		}

		out.setMissingNumber(findMissingNumber(listSorted,listLength));
		out.setSortList(convertListToSting(listSorted));
		out.setCreateAt(LocalDateTime.now());

		orderRepository.save(out);
		return out;
	}

	@Override
	public Order calculateRandom(Integer maxValue) {
		List<Integer> randonList = generateRandonList(maxValue);

		Order out = new Order();
		out.setMissingNumber(findMissingNumber(randonList,maxValue));
		out.setOriginalList(convertListToSting(randonList));
		out.setCreateAt(LocalDateTime.now());

		orderRepository.save(out);
		return out;
	}

	@Override
	public Order calculateFibonacci(Integer value) {
		List<Integer> fibonacciSequence = generateFibonacciSequence(value);
		List<Integer> randomizedSequence = randomizeWithCenter(fibonacciSequence, value);

		Order out = new Order();
		out.setFibonacciList(convertListToSting(randomizedSequence));
		out.setCreateAt(LocalDateTime.now());

		orderRepository.save(out);
		return out;
	}

	@Override
	public List<Order> missingNumbers(Integer lastCalculations) {
		return orderRepository.getOrderDesc().subList(0, lastCalculations);
	}
	
	public static int findMissingNumber(List<Integer> hashSet, int n) {

		for (int i = 1; i <= n; i++) {
			if (!hashSet.contains(i)) {
				return i;
			}
		}

		// If no integer is missing, return MissingNumberException
		logger.warn(NO_MISSING_NUMBER);
		throw new MissingNumberException(NO_MISSING_NUMBER);

	}

	private static List<Integer> sortList(List<Integer> list) {
		return list.stream().sorted().toList();
	}

	private static List<Integer> sortListReverseOrder(List<Integer> list) {
		return list.stream().sorted(Comparator.reverseOrder()).toList();
	}

	private static List<Integer> generateRandonList(Integer maxValue) {

		return RANDOM.ints(1, maxValue + 1)
				.distinct()
				.limit(maxValue - 1L)
				.boxed()
				.toList();
	}

	public static List<Integer> generateFibonacciSequence(int value) {
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

	public static List<Integer> randomizeWithCenter(List<Integer> sequence, int center) {
		List<Integer> randomizedSequence = new ArrayList<>(sequence);
		Collections.shuffle(randomizedSequence);

		int centerIndex = randomizedSequence.indexOf(center);

		// Move the center element to the middle if it's not already there
		if (centerIndex != randomizedSequence.size() / 2) {
			Collections.swap(randomizedSequence, centerIndex, randomizedSequence.size() / 2);
		}

		return randomizedSequence;
	}

	private static String convertListToSting(List<Integer> list) {
		return list.stream()
				.map(String::valueOf)
				.collect(Collectors.joining(", ", "{", "}"));
	}

}
