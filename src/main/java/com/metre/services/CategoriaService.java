/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metre.services;

import com.metre.repositories.CategoriaRepository;
import com.metre.domain.Categoria;
import com.metre.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Renato
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository cRepository;

    public Categoria buscarPorID(Integer id) {
        Optional<Categoria> cat = cRepository.findById(id);
        return cat.orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado!" + id + ", Tipo: " + Categoria.class.getName()));
    }
}
