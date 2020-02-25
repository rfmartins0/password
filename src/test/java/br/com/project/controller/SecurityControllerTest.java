package br.com.project.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.project.dto.PasswordDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SecurityControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private final URI getUri() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/v1/api/password";
		return new URI(baseUrl);
	}

	private static final HttpEntity<PasswordDto> buildPasswordDto(String password) {
		PasswordDto passwordDto = new PasswordDto();
		passwordDto.setPassword(password);
		return new HttpEntity<>(passwordDto);
	}

	@Test
	public void testSenhaComCaracteresInferior() throws URISyntaxException {
		HttpEntity<PasswordDto> request = buildPasswordDto("1#Ssaaaa");
		ResponseEntity<Boolean> result = this.restTemplate.postForEntity(this.getUri(), request, Boolean.class);
		assertFalse(result.getBody());
	}

	@Test
	public void testCodeSenhaComCaracteresInferior() throws URISyntaxException {
		HttpEntity<PasswordDto> request = buildPasswordDto("1#Ssaaaa");
		ResponseEntity<Boolean> result = this.restTemplate.postForEntity(this.getUri(), request, Boolean.class);
		assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testSenhaOK() throws URISyntaxException {
		HttpEntity<PasswordDto> request = buildPasswordDto("11arafRt#23E");
		ResponseEntity<Boolean> result = this.restTemplate.postForEntity(this.getUri(), request, Boolean.class);
		assertTrue(result.getBody());
	}

	@Test
	public void testCodeSenhaOK() throws URISyntaxException {
		HttpEntity<PasswordDto> request = buildPasswordDto("11arafRt#23E");
		ResponseEntity<Boolean> result = this.restTemplate.postForEntity(this.getUri(), request, Boolean.class);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testSenhaOKExatosNoveCaracteres() throws URISyntaxException {
		HttpEntity<PasswordDto> request = buildPasswordDto("11aaR$#@4");
		ResponseEntity<Boolean> result = this.restTemplate.postForEntity(this.getUri(), request, Boolean.class);
		assertTrue(result.getBody());
	}

	@Test
	public void testCodeSenhaOKExatosNoveCaracteres() throws URISyntaxException {
		HttpEntity<PasswordDto> request = buildPasswordDto("11aaR$#@4");
		ResponseEntity<Boolean> result = this.restTemplate.postForEntity(this.getUri(), request, Boolean.class);
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testCodeSenhaSemNumeros() throws URISyntaxException {
		HttpEntity<PasswordDto> request = buildPasswordDto("TTarafdssfRt#E");
		ResponseEntity<Boolean> result = this.restTemplate.postForEntity(this.getUri(), request, Boolean.class);
		assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testSenhaSemNumeros() throws URISyntaxException {
		HttpEntity<PasswordDto> request = buildPasswordDto("TTarafdssfRt#E");
		ResponseEntity<Boolean> result = this.restTemplate.postForEntity(this.getUri(), request, Boolean.class);
		assertFalse(result.getBody());
	}

	@Test
	public void testSenhaSemLetras() throws URISyntaxException {
		HttpEntity<PasswordDto> request = buildPasswordDto("111151554545#25");
		ResponseEntity<Boolean> result = this.restTemplate.postForEntity(this.getUri(), request, Boolean.class);
		assertFalse(result.getBody());
	}

	@Test
	public void testCodeSenhaSemLetras() throws URISyntaxException {
		HttpEntity<PasswordDto> request = buildPasswordDto("111151554545#25");
		ResponseEntity<Boolean> result = this.restTemplate.postForEntity(this.getUri(), request, Boolean.class);
		assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testSenhaSemCaracteresEspeciais() throws URISyntaxException {
		HttpEntity<PasswordDto> request = buildPasswordDto("1dsdsdsdRRE55454525");
		ResponseEntity<Boolean> result = this.restTemplate.postForEntity(this.getUri(), request, Boolean.class);
		assertFalse(result.getBody());
	}

	@Test
	public void testCodeSenhaSemCaracteresEspeciais() throws URISyntaxException {
		HttpEntity<PasswordDto> request = buildPasswordDto("1dsdsdsdRRE55454525");
		ResponseEntity<Boolean> result = this.restTemplate.postForEntity(this.getUri(), request, Boolean.class);
		assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testSenhaSemLetrasMaiusculas() throws URISyntaxException {
		HttpEntity<PasswordDto> request = buildPasswordDto("1dsd#sdsdddd55454525");
		ResponseEntity<Boolean> result = this.restTemplate.postForEntity(this.getUri(), request, Boolean.class);
		assertFalse(result.getBody());
	}

	@Test
	public void testCodeSenhaSemLetrasMaiusculas() throws URISyntaxException {
		;
		HttpEntity<PasswordDto> request = buildPasswordDto("1dsd#sdsdddd55454525");
		ResponseEntity<Boolean> result = this.restTemplate.postForEntity(this.getUri(), request, Boolean.class);
		assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testCodeSenhaSemLetrasMinusculas() throws URISyntaxException {
		HttpEntity<PasswordDto> request = buildPasswordDto("1SSSSLQUURU#@5454525");
		ResponseEntity<Boolean> result = this.restTemplate.postForEntity(this.getUri(), request, Boolean.class);
		assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testSenhaSemLetrasMinusculas() throws URISyntaxException {
		HttpEntity<PasswordDto> request = buildPasswordDto("1SSSSLQUURU#@5454525");
		ResponseEntity<Boolean> result = this.restTemplate.postForEntity(this.getUri(), request, Boolean.class);
		assertFalse(result.getBody());
	}

}
