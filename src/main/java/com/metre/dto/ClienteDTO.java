/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metre.dto;

import com.metre.domain.Categoria;
import com.metre.domain.Cliente;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Renato
 */
public class ClienteDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatório!")
    @Length(min = 5, max = 120, message = "O tamanho máximo deve estar entre 5 e 80 caracteres!")
    private String nome;
    @NotEmpty(message = "Preenchimento obrigatorio!")
    @Email(message = "Email invalido!")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Integer id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public ClienteDTO(Cliente m) {
       this.id = m.getId();
       this.nome = m.getNome();
       this.email = m.getEmail();
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
