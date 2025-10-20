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

    @PostMapping
    public ResponseEntity  save(@RequestBody UsuarioDto dto){
        var usuario = new Usuario();
        BeanUtils.copyProperties(dto,usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
    }
}
