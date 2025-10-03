package com.fatec.estudamaisbackend.repositories;
import com.fatec.estudamaisbackend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
