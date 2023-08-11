package com.epam.gymapp.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(unique = true)
	private String userName;
	@Size(min = 6,message = "Password should be atleast 6 charachters")
	private String password;
	private boolean isActive=true;

	@Column(nullable = false)
	private LocalDate createdDate = LocalDate.now();
	

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Trainee trainee;
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Trainer trainer;

}
