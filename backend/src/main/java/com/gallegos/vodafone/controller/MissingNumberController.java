package com.gallegos.vodafone.controller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gallegos.vodafone.model.ElementsList;
import com.gallegos.vodafone.model.Order;
import com.gallegos.vodafone.service.IMissingNumberService;

import java.util.List;


@RestController
@RequestMapping(MissingNumberController.URL)
public class MissingNumberController {

	private static final Logger logger = LogManager.getLogger(MissingNumberController.class);

	static final String URL = "/api/v1";
	static final String CALCULATE = "/calculate";
	
	static final String CALCULATE_SORT = "/calculate_sort";
	
	static final String CALCULATE_RANDOM = "/calculate_random";
	
	static final String CALCULATE_FIBONACCI = "/calculate_fibonacci";
	
	static final String MISSING_NUMBERS = "/missing_numbers";

	static final String MISSING_NUMBER_OUT = "missingNumber:";
	
	@Autowired
	private IMissingNumberService missingNumberService;
	
	
	@PostMapping(MissingNumberController.CALCULATE)
	public ResponseEntity<String> calculate(@RequestBody ElementsList elementsList) {
		logger.info("Calculating the missing number in the list");
		long startTime = System.nanoTime();
		Order out = missingNumberService.calculate(elementsList);
		long endTime = System.nanoTime();
		logger.info("Missing number in the list calculated in {} seconds.", (double)(endTime - startTime)/1000000000 );

		return ResponseEntity.ok().body(MISSING_NUMBER_OUT + out.getMissingNumber());
	}
	
	@PostMapping(MissingNumberController.CALCULATE_SORT)
	public ResponseEntity<String> calculateSort(@RequestBody ElementsList elementsList) {
		logger.info("Calculating the missing number in the list and sorting the list");
		long startTime = System.nanoTime();
		Order out = missingNumberService.calculateSort(elementsList.getList(), elementsList.getSort());
		long endTime = System.nanoTime();
		logger.info("Missing number in the list calculated and sorted list in {} seconds.",(double)(endTime - startTime)/1000000000);

		return ResponseEntity.ok().body(MISSING_NUMBER_OUT + out.getMissingNumber() + ",\nlist: " + out.getSortList());
	}

	@PostMapping(MissingNumberController.CALCULATE_RANDOM)
	public ResponseEntity<String> calculateRandom(@RequestBody ElementsList elementsList) {
		logger.info("Calculating the missing number in a random list generated");
		long startTime = System.nanoTime();
		Order out = missingNumberService.calculateRandom(elementsList.getMaxValue());
		long endTime = System.nanoTime();
		logger.info("Missing number in a random list generated in {} seconds.",(double)(endTime - startTime)/1000000000);

		return ResponseEntity.ok().body(MISSING_NUMBER_OUT + out.getMissingNumber() + ",\nlist: " + out.getOriginalList());
	}

	@PostMapping(MissingNumberController.CALCULATE_FIBONACCI)
	public ResponseEntity<String> calculateFibonacci(@RequestBody ElementsList elementsList) {
		logger.info("Calculating the fibonacci sequence");
		long startTime = System.nanoTime();
		Order out = missingNumberService.calculateFibonacci(elementsList.getValue());
		long endTime = System.nanoTime();
		logger.info("Fibonacci sequence calculated in {} seconds.",(double)(endTime - startTime)/1000000000);

		return ResponseEntity.ok().body("Fibonacci_List:" + out.getFibonacciList());

	}

	@PostMapping(MissingNumberController.MISSING_NUMBERS)
	public ResponseEntity<String> missingNumbers(@RequestBody ElementsList elementsList) {
		logger.info("Calculating the last {} orders executed", elementsList.getLastCalculations());
		long startTime = System.nanoTime();
		List<Order> out = missingNumberService.missingNumbers(elementsList.getLastCalculations());

		long endTime = System.nanoTime();
		logger.info("Last {} orders executed in {} seconds.", elementsList.getLastCalculations(),(double)(endTime - startTime)/1000000000);
		return ResponseEntity.status(HttpStatus.OK).body(out.toString());
	}
}
