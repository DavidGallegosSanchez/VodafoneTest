package com.gallegos.vodafone.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ElementsList {

	private List<Integer> list;

	private String sort;

	private Integer maxValue;

	private Integer value;

	private Integer lastCalculations;

}
