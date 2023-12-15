package com.gallegos.vodafone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Tbl_Order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_Order")
	private Long id;

	@Column(name = "Create_At")
	private LocalDateTime createAt;

	@Column(name = "Missing_Number")
	private Integer missingNumber;

	@Column(name = "Original_List")
	private String originalList;

	@Column(name = "Sort_List")
	private String sortList;

	@Column(name = "Fibonacci_List")
	private String fibonacciList;

	@Override
	public String toString() {
		return "\n{order: " + id + ",\n" +
				(missingNumber!=null ? " missingNumber=" + missingNumber  + "\n": "") +
				(originalList!=null ? " originalList='" + originalList + '\'' + "\n" : "") +
				(sortList!=null ? " sortList='" + sortList + '\'' + "\n" : "") +
				(fibonacciList!=null ? " fibonacciList='" + fibonacciList + '\'' + "\n" : "") +
				'}';
	}
}
