/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metre.services;

import com.metre.domain.Categoria;
import com.metre.domain.Cliente;
import com.metre.repositories.ClienteRepository;
import com.metre.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Renato
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository cRepository;

    public Cliente buscarPorID(Integer id) {
        Optional<Cliente> cat = cRepository.findById(id);
        return cat.orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado!" + id + ", Tipo: " + Cliente.class.getName()));
    }
}
