package br.edu.amintasvrp.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.amintasvrp.ws.model.Usuario;
import br.edu.amintasvrp.ws.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public Usuario cadastrar(Usuario usuario) {
		return usuarioRepository.save(usuario);		
	}

	public Usuario buscarPorNome(String nome) {
		return usuarioRepository.buscarPorNome(nome);
	}
	
	
}
