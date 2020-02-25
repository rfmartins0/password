package br.com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.dto.PasswordDto;
import br.com.project.service.SecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Api(value = "SecurityResource")
@RestController
@RequestMapping("/v1/api/")
public class SecurityController {
	
	@Autowired
	private SecurityService securityService;

	@ApiOperation(value = "Verifica se a senha informada atende aos requisitos mínimos", httpMethod = "POST")
	@ApiResponse(code = 200, message = "Senha atende aos padrões de segurança")
	@PostMapping(path = "/password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> verify(@RequestBody final PasswordDto passwordDto) {
		if (securityService.isValid(passwordDto)) {
			return ResponseEntity.ok(Boolean.TRUE);
		}
		return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
	}

}
