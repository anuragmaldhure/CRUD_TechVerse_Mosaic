package com.app.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "address")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "emp",callSuper = true)
public class Address extends BaseEntity {
	@Column(length = 50)
	private String street;
	@Column(length = 20)
	private String city;
	@Column(length = 30)
	private String state;
	@Column(length = 30)
	private String country;
	@Column(length = 10, name = "zip_code")
	private String zipCode;
	// association mapping property
	// Demo of uni dir association , using shared PK approach
	@OneToOne // can be configured as : (fetch=FetchType.LAZY) , if required
	@JoinColumn(name = "employee_id", nullable = false)
	@MapsId
	private Employee emp;

	public Address(String street, String city, String state, String country, String zipCode) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
	}
}
