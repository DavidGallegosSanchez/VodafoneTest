package com.gallegos.vodafone.service;

import org.junit.jupiter.api.Assertions;
import com.gallegos.vodafone.model.ElementsList;
import com.gallegos.vodafone.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MissingNumberServiceTest {

    @Autowired
    MissingNumberServiceImpl missingNumberService;

    @Test
    void calculateTest(){
        //Given
        ElementsList elementsList = generateElementsList();

        //When
        Order order = missingNumberService.calculate(elementsList);

        //Then
        Assertions.assertNotNull(order, "Order should be not null.");
        Assertions.assertNotNull(order.getMissingNumber(), "Missing number should be not null.");
        Assertions.assertEquals(4, order.getMissingNumber(), "The value of missing number should be 4.");
    }

    @Test
    void calculateMissingIntegerTest(){
        //Given
        ElementsList elementsList = generateElementsList();
        elementsList.setList(List.of(1,2,3,4,5,6));

        //When

        //Then
        Assertions.assertThrows(MissingNumberException.class, () -> {
            missingNumberService.calculate(elementsList);
        });
    }

    @Test
    void calculateSortASCTest() throws MissingNumberException {
        //Given
        ElementsList elementsList = generateElementsList();
        String sortType = "low_high";

        //When
        Order order = missingNumberService.calculateSort(elementsList.getList(), sortType);

        //Then
        Assertions.assertNotNull(order, "Order should be not null.");
        Assertions.assertNotNull(order.getMissingNumber(), "Missing number should be not null.");
        Assertions.assertEquals(4, order.getMissingNumber(), "The value of missing number should be 4.");
        Assertions.assertNotNull(order.getSortList(), "Sort list should be not null.");
        Assertions.assertEquals("{1, 2, 3, 5, 6}", order.getSortList(), "The value of Sort list should be {1, 2, 3, 5, 6}.");
    }

    @Test
    void calculateSortDESCTest() throws MissingNumberException {
        //Given
        ElementsList elementsList = generateElementsList();
        String sortType = "high_low";

        //When
        Order order = missingNumberService.calculateSort(elementsList.getList(), sortType);

        //Then
        Assertions.assertNotNull(order, "Order should be not null.");
        Assertions.assertNotNull(order.getMissingNumber(), "Missing number should be not null.");
        Assertions.assertEquals(4, order.getMissingNumber(), "The value of missing number should be 4.");
        Assertions.assertNotNull(order.getSortList(), "Sort list should be not null.");
        Assertions.assertEquals("{6, 5, 3, 2, 1}", order.getSortList(), "The value of Sort list should be {6, 5, 3, 2, 1}.");
    }

    @Test
    void calculateSortWrongTest() throws MissingNumberException {
        //Given
        String sortType = "Wrong";
        List<Integer> elementsList = List.of(1,2,3,4,5,6);

        //When

        //Then
        Assertions.assertThrows(MissingNumberException.class, () -> {
            missingNumberService.calculateSort(elementsList, sortType);
        });
    }

    @Test
    void calculateRandomTest(){
        //Given

        //When
        Order order = missingNumberService.calculateRandom(8);

        //Then
        Assertions.assertNotNull(order, "Order should be not null.");
        Assertions.assertNotNull(order.getMissingNumber(), "Missing number should be not null.");
        Assertions.assertNotNull(order.getOriginalList(), "Original list should be not null.");
    }

    @Test
    void calculateFibonacciTest(){
        //Given

        //When
        Order order = missingNumberService.calculateFibonacci(21);

        //Then
        Assertions.assertNotNull(order, "Order should be not null.");
        Assertions.assertNotNull(order.getFibonacciList(), "Fibonacci list should be not null.");
        Assertions.assertTrue(order.getFibonacciList().contains("21"), "Fibonacci list should contain 21 value.");
    }

    @Test
    void missingNumbersTest() throws MissingNumberException {
        //Given
        ElementsList elementsList = generateElementsList();
        missingNumberService.calculate(elementsList); // Order 1
        missingNumberService.calculateSort(elementsList.getList(), "low_high");  // Order 2
        missingNumberService.calculateSort(elementsList.getList(), "high_low");  // Order 3

        //When
        List<Order> orderList = missingNumberService.missingNumbers(2);

        //Then
        Assertions.assertNotNull(orderList, "Order should be not null.");
        Assertions.assertEquals(2, orderList.size(), "Order list should contain 2 values.");
    }

    @Test
    void missingNumbersNoOrderStoredTest() throws MissingNumberException {
        //Given

        //When

        //Then
        Assertions.assertThrows(MissingNumberException.class, () -> {
            missingNumberService.missingNumbers(2);
        });
    }
    private ElementsList generateElementsList(){
        ElementsList elementsList = new ElementsList();
        elementsList.setList(List.of(1,3,2,6,5));
        elementsList.setSort("low_high");
        elementsList.setMaxValue(8);
        elementsList.setValue(21);
        elementsList.setLastCalculations(2);

        return elementsList;
    }
}
