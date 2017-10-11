package br.com.fabricadeprogramador.ws.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabricadeprogramador.ws.model.Usuario;
import br.com.fabricadeprogramador.ws.service.UsuarioService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@RestController
public class LoginController{
	
	@Autowired
	UsuarioService usuarioService;
	
	
	@RequestMapping( value ="/autenticar", consumes=MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.POST	)
	public LoginResponse autenticar(@RequestBody Usuario usuario) throws ServletException {
		
		System.out.println(usuario.getNome() + " " + usuario.getSenha());
		
		if (usuario.getNome() == null || usuario.getSenha() == null)
		{
			throw new ServletException("Nome e senha obrigatórios");
		}
		
		Usuario usuAtenticado = usuarioService.BuscarPorNome(usuario.getNome());
		if (usuAtenticado==null) {
			throw new ServletException("Usuário não encontrado");			
		}
		
		if(!usuAtenticado.getSenha().equals(usuario.getSenha()) ){
			throw new ServletException("Nome e senha inválidos");
		}
			
		
		//return new ResponseEntity<Usuario>(usuAtenticado, HttpStatus.OK);
		//TOKEN {token=mninjioasjdosajdjsadjosdjosajd}
	    String token = Jwts.builder()
	    		.setSubject(usuAtenticado.getNome())
	    		.signWith(SignatureAlgorithm.HS512, "carolcastro")
	    		.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
	    		.compact();
	    
	    
		return new LoginResponse(token);
		
		
	}
	
	
	private class LoginResponse{
			public String token;
			
			public String getToken() {
				return token;
			}
			public void setToken(String token) {
				token = token;
			}
			public LoginResponse(String token) {
				this.token= token;
			}
	}
	
	
}