package com.gallegos.vodafone.controller;

import com.gallegos.vodafone.mapper.Mapper;
import com.gallegos.vodafone.model.ElementsList;
import com.gallegos.vodafone.model.dto.CalculateDto;
import com.gallegos.vodafone.model.dto.CalculateFibonacciDto;
import com.gallegos.vodafone.model.dto.CalculateRandomDto;
import com.gallegos.vodafone.model.dto.CalculateSortDto;
import com.gallegos.vodafone.service.FrontMissingNumberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(FrontMissingNumberController.URL)
public class FrontMissingNumberController {
    private static final Logger logger = LogManager.getLogger(FrontMissingNumberController.class);

    static final String URL = "/api";
    static final String CALCULATE = "calculate";

    static final String CALCULATE_SORT = "/calculate_sort";

    static final String CALCULATE_RANDOM = "/calculate_random";

    static final String CALCULATE_FIBONACCI = "/calculate_fibonacci";

    @Autowired
    FrontMissingNumberService frontMissingNumberService;

    @Autowired
    private Mapper mapper;

    @PostMapping(FrontMissingNumberController.CALCULATE)
    public CalculateDto calculate(@RequestBody ElementsList elementsList){
        logger.info("Calculating the missing number in the list");
        long startTime = System.nanoTime();

        CalculateDto calculateDto = mapper.toDto(frontMissingNumberService.calculate(elementsList));

        long endTime = System.nanoTime();
        logger.info("Missing number in the list calculated in {} seconds.", (double)(endTime - startTime)/1000000000 );

        return calculateDto;
    }

    @PostMapping(MissingNumberController.CALCULATE_SORT)
    public CalculateSortDto calculateSort(@RequestBody ElementsList elementsList) {
        logger.info("Calculating the missing number in the list and sorting the list");
        long startTime = System.nanoTime();
        CalculateSortDto calculateSortDto = mapper.toDto(frontMissingNumberService.calculateSort(elementsList.getList(), elementsList.getSort()));
        long endTime = System.nanoTime();
        logger.info("Missing number in the list calculated and sorted list in {} seconds.",(double)(endTime - startTime)/1000000000);

        return calculateSortDto;
    }

    @PostMapping(MissingNumberController.CALCULATE_RANDOM)
    public CalculateRandomDto calculateRandom(@RequestBody ElementsList elementsList) {
        logger.info("Calculating the missing number in a random list generated");
        long startTime = System.nanoTime();
        CalculateRandomDto calculateRandomDto = mapper.toDto(frontMissingNumberService.calculateRandom(elementsList.getMaxValue()));
        long endTime = System.nanoTime();
        logger.info("Missing number in a random list generated in {} seconds.",(double)(endTime - startTime)/1000000000);

        return calculateRandomDto;
    }

    @PostMapping(MissingNumberController.CALCULATE_FIBONACCI)
    public CalculateFibonacciDto calculateFibonacci(@RequestBody ElementsList elementsList) {
        logger.info("Calculating the fibonacci sequence");
        long startTime = System.nanoTime();
        CalculateFibonacciDto out = mapper.toDto(frontMissingNumberService.calculateFibonacci(elementsList.getValue()));
        long endTime = System.nanoTime();
        logger.info("Fibonacci sequence calculated in {} seconds.",(double)(endTime - startTime)/1000000000);

        return out;
    }
}
