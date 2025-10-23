package com.fatec.estudamaisbackend.controllers;


import com.fatec.estudamaisbackend.dtos.UsuarioDto;
import com.fatec.estudamaisbackend.model.Usuario;
import com.fatec.estudamaisbackend.repositories.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @GetMapping
    public ResponseEntity getAll(){
        List<Usuario> listUsuarios = repository.findAll();
                return ResponseEntity.status(HttpStatus.OK).body(listUsuarios);
    }


    @GetMapping("/{id}")
        public ResponseEntity getById(@PathVariable(value = "id") Integer id){
        Optional usuario = repository.findById(id);
      if (usuario.isEmpty()){
          return ResponseEntity.status(HttpStatus.FOUND).body("User not found");
      }
        return ResponseEntity.status(HttpStatus.FOUND).body(usuario.get());
    }


    @PostMapping
    public ResponseEntity  save(@RequestBody UsuarioDto dto){
        var usuario = new Usuario();
        BeanUtils.copyProperties(dto,usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Integer id){
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        repository.delete(usuario.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Integer id, @RequestBody UsuarioDto dto){
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        var usuarioModel = usuario.get();
        BeanUtils.copyProperties(dto,usuarioModel);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuarioModel));
    }
}
