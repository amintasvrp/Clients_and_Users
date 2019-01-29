package br.edu.amintasvrp.ws.controller;



import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.amintasvrp.ws.model.Usuario;
import br.edu.amintasvrp.ws.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {
	
	@Autowired
	public UsuarioService usuarioService;
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/autenticar",consumes=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.POST)
	public LoginResponse autenticar(@RequestBody Usuario usuario) throws ServletException {
		
		if(usuario.getNome() == null || usuario.getSenha() == null) {
			throw new ServletException("Nome e Senha Obrigatórios");
		}
		
		
		Usuario usuAutenticado = usuarioService.buscarPorNome(usuario.getNome());
		
		if(usuAutenticado == null) {
			throw new ServletException("Usuário Não Encontrado");
		}
		
		if(!usuAutenticado.getSenha().equals(usuario.getSenha())) {
			throw new ServletException("Usuário ou Senha Inválido");
		}
		
		//Printar para gerar nova chave em string
		//SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //or HS384 or HS512
		//String base64Key = Encoders.BASE64.encode(key.getEncoded());
		
		
		String token = Jwts.builder()
				.setSubject(usuAutenticado.getNome())
				.signWith(SignatureAlgorithm.HS256, "dqhwcgt8ThsRDNhPDEGqr7e1nDKNK02U8y3/4WoUR5A=")
				.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
				.compact();
		
		return new LoginResponse(token);
		
	}
	
	
	
	private class LoginResponse{
		public String token;
		
		public LoginResponse(String token) {
			this.token = token;
		}
		
	}

}
