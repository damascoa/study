/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metre.dto;

import com.metre.domain.Categoria;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Renato
 */
public class CategoriaDTO implements Serializable{

    
    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatório!")
    @Length(min=5,max = 80, message = "O tamanho máximo deve estar entre 5 e 80 caracteres!")
    private String nome;

    public CategoriaDTO() {
    }
    public CategoriaDTO(Categoria cat) {
        this.id = cat.getId();
        this.nome = cat.getNome();
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
