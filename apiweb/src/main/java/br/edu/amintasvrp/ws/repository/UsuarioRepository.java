package br.edu.amintasvrp.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.amintasvrp.ws.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	// Select u from Usuario u where u.nome=:pnome 
	@Query(value="SELECT * FROM USUARIO WHERE NOME = :pnome", nativeQuery = true)
	public Usuario buscarPorNome(@Param("pnome") String nome);
}
