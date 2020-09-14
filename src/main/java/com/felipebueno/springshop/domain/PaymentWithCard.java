package com.felipebueno.springshop.domain;

import javax.persistence.Entity;

import com.felipebueno.springshop.domain.enums.StatePayment;

@Entity
public class PaymentWithCard extends Payment {
	private static final long serialVersionUID = 1L;

	private Integer checkoutsNumber;

	public PaymentWithCard() {
	}

	public PaymentWithCard(Integer id, StatePayment state, Demand demand, Integer checkoutsNumber) {
		super(id, state, demand);
		this.checkoutsNumber = checkoutsNumber;
	}

	public Integer getCheckoutsNumber() {
		return checkoutsNumber;
	}

	public void setCheckoutsNumber(Integer checkoutsNumber) {
		this.checkoutsNumber = checkoutsNumber;
	}

}
