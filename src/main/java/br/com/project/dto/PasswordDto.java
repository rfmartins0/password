package br.com.project.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Password")
public class PasswordDto {

	@ApiModelProperty(value = "Para a senha ser válida ela deve conter ao menos 9 caracteres"
			+ "\n um caracter especial, um número e uma letra.", example="Vrtre#$12390Tp", required=true)
	@NotNull
	@Pattern(regexp = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{9,})")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    
}
