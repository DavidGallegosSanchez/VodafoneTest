package com.gallegos.vodafone.mapper;

import com.gallegos.vodafone.model.Calculate;
import com.gallegos.vodafone.model.dto.CalculateDto;
import org.springframework.stereotype.Component;

@Component
public class CalculateMapper {

    public CalculateDto toDto(Calculate calculate){
        CalculateDto dto = new CalculateDto();
        dto.setMissingNumber(calculate.getMissingNumber());
        return dto;
    }

    public Calculate toCalculate(CalculateDto calculateDto){
        return new Calculate(calculateDto.getMissingNumber());
    }
}
