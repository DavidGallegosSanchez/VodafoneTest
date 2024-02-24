package com.gallegos.vodafone.mapper;

import com.gallegos.vodafone.model.Calculate;
import com.gallegos.vodafone.model.CalculateFibonacci;
import com.gallegos.vodafone.model.CalculateRandom;
import com.gallegos.vodafone.model.CalculateSort;
import com.gallegos.vodafone.model.dto.CalculateDto;
import com.gallegos.vodafone.model.dto.CalculateFibonacciDto;
import com.gallegos.vodafone.model.dto.CalculateRandomDto;
import com.gallegos.vodafone.model.dto.CalculateSortDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public CalculateDto toDto(Calculate calculate){
        CalculateDto dto = new CalculateDto();
        dto.setMissingNumber(calculate.getMissingNumber());

        return dto;
    }

    public Calculate toCalculate(CalculateDto calculateDto){
        return new Calculate(calculateDto.getMissingNumber());
    }

    public CalculateFibonacciDto toDto(CalculateFibonacci calculateFibonacci){
        return new CalculateFibonacciDto(calculateFibonacci.getFibonacciList());
    }

    public CalculateFibonacci toCalculateFibonacci(CalculateFibonacciDto calculateFibonacciDto){
        return new CalculateFibonacci(calculateFibonacciDto.getFibonacciList());
    }

    public CalculateRandomDto toDto(CalculateRandom calculateRandom){
        return new CalculateRandomDto(calculateRandom.getMissingNumber(), calculateRandom.getList());
    }

    public CalculateRandom toCalculateRandom(CalculateRandomDto calculateRandomDto){
        return new CalculateRandom(calculateRandomDto.getMissingNumber(), calculateRandomDto.getList());
    }

    public CalculateSortDto toDto(CalculateSort calculateSort){
        return new CalculateSortDto(calculateSort.getMissingNumber(), calculateSort.getList());
    }

    public CalculateSort toCalculateSort(CalculateSortDto calculateSortDto){
        return new CalculateSort(calculateSortDto.getMissingNumber(), calculateSortDto.getList());
    }
}
