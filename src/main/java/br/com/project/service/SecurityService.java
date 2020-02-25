package br.com.project.service;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Service;

import br.com.project.dto.PasswordDto;

@Service
public class SecurityService {

	public boolean isValid(final PasswordDto passwordDto) {
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		final Validator validator = factory.getValidator();
		return validator.validate(passwordDto).isEmpty();
	}

}
