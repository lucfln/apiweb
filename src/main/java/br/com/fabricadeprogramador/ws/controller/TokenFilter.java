package br.com.fabricadeprogramador.ws.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class TokenFilter extends GenericFilterBean{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest  req = (HttpServletRequest) request;
		
		String header = req.getHeader("Authorization");
				
		if(header==null ||  !header.startsWith("Bearer ")) {
			throw new ServletException("Token Inexistente ou invalido");			
			
		};
		
		String token = header.substring(7); //extraindo somente o string do token sem o Bearer
		
		try {
			Jwts.parser().setSigningKey("carolcastro").parseClaimsJws(token).getBody();
		}catch(SignatureException e){
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token Inválido!");
			//throw new ServletException("Token Inválido");
		}
		
		chain.doFilter(request, response);
		
		
	}
}
