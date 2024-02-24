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

	@Autowired
	IOrderRepository orderRepository;

	@Autowired
	UtilService utilService;

	@Override
	public Order calculate(ElementsList elementsList) {

		Order out = new Order();
		int listLength = elementsList.getList().size();

		List<Integer> listSorted = utilService.sortList(elementsList.getList());
		
		out.setMissingNumber(utilService.findMissingNumber(listSorted,listLength));
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
			listSorted = utilService.sortList(elementsLits);
		}else if("high_low".equals(sortType)){
			listSorted = utilService.sortListReverseOrder(elementsLits);
		} else {
			logger.error(WRONG_SORT_VALUE);
			throw new MissingNumberException(WRONG_SORT_VALUE);
		}

		out.setMissingNumber(utilService.findMissingNumber(listSorted,listLength));
		out.setSortList(convertListToSting(listSorted));
		out.setCreateAt(LocalDateTime.now());

		orderRepository.save(out);
		return out;
	}

	@Override
	public Order calculateRandom(Integer maxValue) {
		List<Integer> randonList = utilService.generateRandonList(maxValue);

		Order out = new Order();
		out.setMissingNumber(utilService.findMissingNumber(randonList,maxValue));
		out.setOriginalList(convertListToSting(randonList));
		out.setCreateAt(LocalDateTime.now());

		orderRepository.save(out);
		return out;
	}

	@Override
	public Order calculateFibonacci(Integer value) {
		List<Integer> fibonacciSequence = utilService.generateFibonacciSequence(value);
		List<Integer> randomizedSequence = utilService.randomizeWithCenter(fibonacciSequence, value);

		Order out = new Order();
		out.setFibonacciList(convertListToSting(randomizedSequence));
		out.setCreateAt(LocalDateTime.now());

		orderRepository.save(out);
		return out;
	}

	@Override
	public List<Order> missingNumbers(Integer lastCalculations) throws MissingNumberException{

		List<Order> orders = orderRepository.getOrderDesc();

		if(orders.size() < lastCalculations){
			throw new MissingNumberException("There are fewer orders stored than you are requesting.");
		}

		return orders.subList(0, lastCalculations);
	}

	private static String convertListToSting(List<Integer> list) {
		return list.stream()
				.map(String::valueOf)
				.collect(Collectors.joining(", ", "{", "}"));
	}

}
