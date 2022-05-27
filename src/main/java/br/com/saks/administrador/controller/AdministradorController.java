/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saks.administrador.controller;

import br.com.saks.administrador.model.Administrador;
import br.com.saks.administrador.repository.AdministradorRepository;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administrador")

public class AdministradorController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @GetMapping
    public List<Administrador> listarTodos() {
        return administradorRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Administrador> listarPeloIdEmprestimo(@PathVariable Long id) {
        return administradorRepository.findById(id);
    }

    @PostMapping
    public Administrador adicionar(@RequestBody Administrador adm) {
        return administradorRepository.save(adm);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Administrador adm) {
        return administradorRepository.findById(id)
                .map(record -> {
                    record.setNome(adm.getNome());
                    record.setEmail(adm.getEmail());
                    record.setSenha(adm.getSenha());
                    record.setStatus(adm.getStatus());
                    Administrador admUpdated = administradorRepository.save(record);
                    return ResponseEntity.ok().body(admUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {

        return administradorRepository.findById(id)
                .map(record -> {
                    record.setStatus(0);
                    Administrador admDelete = administradorRepository.save(record);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }



    public static String getSHA256(String senha) {

        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(senha.getBytes("utf8"));
            toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }   }