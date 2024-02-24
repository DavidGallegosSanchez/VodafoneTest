package com.gallegos.vodafone.service;

import com.gallegos.vodafone.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrontMissingNumberService implements IFrontMissingNumberService{
    private static final Logger logger = LogManager.getLogger(UtilService.class);

    static final String WRONG_SORT_VALUE = "Sort value should be low_high or high_low";

    @Autowired
    UtilService utilService;

    @Override
    public Calculate calculate(ElementsList elementsList) {
        Calculate out = new Calculate();
        int listLength = elementsList.getList().size();

        List<Integer> listSorted = utilService.sortList(elementsList.getList());

        out.setMissingNumber(utilService.findMissingNumber(listSorted,listLength));

        return out;
    }

    @Override
    public CalculateSort calculateSort(List<Integer> elementsList, String sortType) throws MissingNumberException {
        CalculateSort out = new CalculateSort();

        int listLength = elementsList.size();
        List<Integer> listSorted;

        if("low_high".equals(sortType)){
            listSorted = utilService.sortList(elementsList);
        }else if("high_low".equals(sortType)){
            listSorted = utilService.sortListReverseOrder(elementsList);
        } else {
            logger.error(WRONG_SORT_VALUE);
            throw new MissingNumberException(WRONG_SORT_VALUE);
        }

        out.setMissingNumber(utilService.findMissingNumber(listSorted,listLength));
        out.setList(listSorted);

        return out;
    }

    @Override
    public CalculateRandom calculateRandom(Integer maxValue) {
        List<Integer> randonList = utilService.generateRandonList(maxValue);

        CalculateRandom out = new CalculateRandom();
        out.setMissingNumber(utilService.findMissingNumber(randonList,maxValue));
        out.setList(randonList);

        return out;
    }

    @Override
    public CalculateFibonacci calculateFibonacci(Integer value) {
        List<Integer> fibonacciSequence = utilService.generateFibonacciSequence(value);
        List<Integer> randomizedSequence = utilService.randomizeWithCenter(fibonacciSequence, value);

        CalculateFibonacci out = new CalculateFibonacci();
        out.setFibonacciList(randomizedSequence);

        return out;
    }
}
