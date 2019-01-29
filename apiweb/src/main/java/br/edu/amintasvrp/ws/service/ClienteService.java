package br.edu.amintasvrp.ws.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.amintasvrp.ws.model.Cliente;
import br.edu.amintasvrp.ws.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public Cliente cadastrar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Collection<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	public void excluir(Cliente cliente) {
		clienteRepository.delete(cliente);
	}

	public Optional<Cliente> buscarPorId(Integer id) {
		return clienteRepository.findById(id);
	}

	public Cliente alterar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

}
