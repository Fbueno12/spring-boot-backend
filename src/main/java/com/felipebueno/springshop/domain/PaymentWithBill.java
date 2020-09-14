package com.felipebueno.springshop.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.felipebueno.springshop.domain.enums.StatePayment;

@Entity
public class PaymentWithBill extends Payment {
	private static final long serialVersionUID = 1L;

	private Date deadline;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date paymentDate;

	public PaymentWithBill() {
	}

	public PaymentWithBill(Integer id, StatePayment state, Demand demand, Date deadline, Date paymentDate) {
		super(id, state, demand);
		this.deadline = deadline;
		this.paymentDate = paymentDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}
