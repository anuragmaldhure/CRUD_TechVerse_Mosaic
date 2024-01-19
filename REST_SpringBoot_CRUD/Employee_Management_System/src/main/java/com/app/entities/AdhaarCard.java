package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable //to tell hibernate , following class is a composite value type , 
//whose instance is going to be embedded in the owning entity
public class AdhaarCard {
	@Column(name="card_number",length = 20,unique = true)
	private String cardNumber;
	@Column(length = 30)
	private String location;
	@Column(name="created_on")
	private LocalDate createdOn;
	public AdhaarCard() {
		// TODO Auto-generated constructor stub
	}
	public AdhaarCard(String cardNumber, String location, LocalDate createdOn) {
		super();
		this.cardNumber = cardNumber;
		this.location = location;
		this.createdOn = createdOn;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalDate getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}
	@Override
	public String toString() {
		return "AdhaarCard [cardNumber=" + cardNumber + ", location=" + location + ", createdOn=" + createdOn + "]";
	}
	
}
