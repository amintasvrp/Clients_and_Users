package br.edu.amintasvrp.ws.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Jwts;

public class TokenFilter extends GenericFilterBean{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		String header = req.getHeader("Authorization");
		if(header == null || !header.startsWith("Bearer ")) {
			throw new ServletException("Token Inválido ou Inexiste");
		}
		
		String token = header.substring(7); //Extraindo somente a string do Token dem o Bearer
		
		// Verificar se o Token é válido
		// Chave gerada em LoginController
		try {
			Jwts.parser().setSigningKey("dqhwcgt8ThsRDNhPDEGqr7e1nDKNK02U8y3/4WoUR5A=").parseClaimsJws(token).getBody();
		} catch (Exception e) {
			throw new ServletException("Token Inválido");
		}
		
		chain.doFilter(request, response);
		
	}

	
}