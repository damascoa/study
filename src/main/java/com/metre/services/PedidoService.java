/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metre.services;

import com.metre.domain.Pedido;
import com.metre.repositories.PedidoRepository;
import com.metre.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Renato
 */
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pRepository;

    public Pedido buscarPorID(Integer id) {
        Optional<Pedido> cat = pRepository.findById(id);
        return cat.orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado!" + id + ", Tipo: " + Pedido.class.getName()));
    }
}
