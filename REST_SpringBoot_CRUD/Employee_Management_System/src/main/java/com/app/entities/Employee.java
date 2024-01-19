package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "emps")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = { "dept", "card" })
public class Employee extends BaseEntity {
	@Column(length = 20)
	private String firstName;
	@Column(length = 20)
	private String lastName;
	@Column(length = 20, unique = true)
	private String email;
	@Column(length = 25, nullable = false)
	private String password;
	private LocalDate joinDate;
	@Enumerated(EnumType.STRING) // varchar
	@Column(length = 20)
	private EmploymentType empType;
	private double salary;
	@Lob
	private byte[] image;
	private String imagePath;// This will be used for storing n restoring images in server side folder
	// Employee *--->1 Department
	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
	private Department dept;
	// Emp HAS-A AdhaarCard(Emp 1--->1 AdhaarCard) : strong asso (composition)
	@Embedded // optional annotation
	private AdhaarCard card;

	public Employee(String firstName, String lastName, String email, String password, LocalDate joinDate,
			EmploymentType empType, double salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.joinDate = joinDate;
		this.empType = empType;
		this.salary = salary;
	}

}
