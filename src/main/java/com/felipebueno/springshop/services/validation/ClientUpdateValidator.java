package com.felipebueno.springshop.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.felipebueno.springshop.domain.Client;
import com.felipebueno.springshop.payloads.ClientPayload;
import com.felipebueno.springshop.repositories.ClientRepository;
import com.felipebueno.springshop.resources.exception.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientPayload> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ClientRepository repository;

	@Override
	public void initialize(ClientUpdate ann) {
	}

	@Override
	public boolean isValid(ClientPayload payload, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer uriId = Integer.parseInt(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		Client aux = repository.findByEmail(payload.getEmail());

		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "email já está cadastrado em nossa base de dados!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}