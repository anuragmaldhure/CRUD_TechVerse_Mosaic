package com.app.entities;

import java.time.LocalDate;

//all specs Java EE supplied
/*
 *   <tr key={employee.id}>
                <td>{employee.firstName}</td>
                <td>{employee.lastName}</td>
                <td>{employee.email}</td>
                <td>{employee.location}</td>
                <td>{employee.department}</td>
                <td>{employee.joinDate}</td>
                <td>{employee.salary}</td>
                <td>
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "new_emps") // to specify table name
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "password", callSuper = true)
public class Employee extends BaseEntity {

	@Column(length = 20) // varchar(20)
	private String firstName;
	@Column(length = 20)
	private String lastName;
	@Column(length = 30, unique = true) // unique constraint
	private String email;
	@Column(length = 20, nullable = false) // NOT NULL constraint
	private String password;
	private LocalDate joinDate;
	private double salary;
	@Column(length = 30)
	private String location;
	@Column(length = 30)
	private String department;
}
