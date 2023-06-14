package com.assignment.springbootassignment.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "movie")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	@Getter
	@Setter
	private int id;

	@Column (name = "name")
	@Getter
	@Setter
	private String name;
	@Column (name = "year")
	@Getter
	@Setter
	private int year;
	@Column (name = "rating")
	@Getter
	@Setter
	private double rating;

	public Movie(String name, int year, double rating) {
		this.name = name;
		this.year = year;
		this.rating = rating;
	}
}
