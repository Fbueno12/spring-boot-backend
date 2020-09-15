package com.felipebueno.springshop.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.felipebueno.springshop.domain.enums.ClientType;
import com.felipebueno.springshop.payloads.NewClientPayload;
import com.felipebueno.springshop.resources.exception.FieldMessage;
import com.felipebueno.springshop.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, NewClientPayload> {

	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(NewClientPayload payload, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(payload.getClientType().equals(ClientType.PESSOAFISICA.getCode()) && !BR.isValidCPF(payload.getDocument())) {
			list.add(new FieldMessage("document", "CPF inválido"));
		}
		
		if(payload.getClientType().equals(ClientType.PESSOAJURIDICA.getCode()) && !BR.isValidCNPJ(payload.getDocument())) {
			list.add(new FieldMessage("document", "CNPJ inválido"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}