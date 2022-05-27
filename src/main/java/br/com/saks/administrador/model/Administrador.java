package br.com.saks.administrador.model;

import static br.com.saks.administrador.controller.AdministradorController.getSHA256;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import lombok.Data;

@Entity
@Data
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nome;

    @Column(nullable = false, length = 200)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

    @Column
    private Integer status;

    public void setSenha(String senha) {
        this.senha = getSHA256(senha);
    }
}
